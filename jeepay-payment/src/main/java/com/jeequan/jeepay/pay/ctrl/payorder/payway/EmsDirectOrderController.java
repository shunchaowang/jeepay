/*
 * Copyright (c) 2021-2031, 河北计全科技有限公司 (https://www.jeequan.com & jeequan@126.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jeequan.jeepay.pay.ctrl.payorder.payway;

import com.jeequan.jeepay.core.constants.ApiCodeEnum;
import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.core.entity.MchApp;
import com.jeequan.jeepay.core.entity.PayOrder;
import com.jeequan.jeepay.core.exception.BizException;
import com.jeequan.jeepay.core.model.ApiRes;
import com.jeequan.jeepay.pay.ctrl.payorder.AbstractPayOrderController;
import com.jeequan.jeepay.pay.emspay.EmsDirectOrderRQ;
import com.jeequan.jeepay.pay.model.MchAppConfigContext;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRS;
import com.jeequan.jeepay.pay.service.ConfigContextQueryService;
import com.jeequan.jeepay.service.impl.PayWayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * 统一下单 controller
 *
 * @author terrfly
 * @site https://www.jeequan.com
 * @date 2021/6/8 17:27
 */
@Slf4j
@RestController
@Deprecated
public class EmsDirectOrderController extends AbstractPayOrderController {

  private final PayWayService payWayService;
  private final ConfigContextQueryService configContextQueryService;

  public EmsDirectOrderController(PayWayService payWayService,
      ConfigContextQueryService configContextQueryService) {
    this.payWayService = payWayService;
    this.configContextQueryService = configContextQueryService;
  }

  /**
   * 统一下单接口
   *
   **/
  @PostMapping("/api/pay/emsDirectOrder")
  public ApiRes emsDirectOrder(@RequestBody UnifiedOrderRQ rq) {

    // validate the sign
    try {
      verifyMerchAndSign(rq);
    } catch (BizException exception) {
      return exception.getApiRes();
    }
    //获取参数 & 验签
//    UnifiedOrderRQ rq = getRQByWithMchSign(UnifiedOrderRQ.class);

    // get the ems rq
    EmsDirectOrderRQ bizRQ = (EmsDirectOrderRQ) rq.buildBizRQ();

    //实现子类的res
    ApiRes apiRes = unifiedOrder(bizRQ.getWayCode(), bizRQ);
    if (apiRes.getData() == null) {
      return apiRes;
    }

    UnifiedOrderRS bizRes = (UnifiedOrderRS) apiRes.getData();

    //聚合接口，返回的参数
    UnifiedOrderRS res = new UnifiedOrderRS();
    BeanUtils.copyProperties(bizRes, res);

    //只有 订单生成（QR_CASHIER） || 支付中 || 支付成功返回该数据
    if (bizRes.getOrderState() != null && (bizRes.getOrderState() == PayOrder.STATE_INIT
        || bizRes.getOrderState() == PayOrder.STATE_ING
        || bizRes.getOrderState() == PayOrder.STATE_SUCCESS)) {
      res.setPayDataType(bizRes.buildPayDataType());
      res.setPayData(bizRes.buildPayData());
    }

    return ApiRes.okWithSign(res,
        configContextQueryService.queryMchApp(rq.getMchNo(), rq.getAppId()).getAppSecret());
  }

  /**
   * 商户通用验证
   **/
  private void verifyMerchAndSign(UnifiedOrderRQ bizRQ) {

    //业务校验， 包括： 验签， 商户状态是否可用， 是否支持该支付方式下单等。
    String mchNo = bizRQ.getMchNo();
    String appId = bizRQ.getAppId();
    String sign = bizRQ.getSign();

    if (StringUtils.isAnyBlank(mchNo, appId, sign)) {
      throw new BizException("参数有误！");
    }

    MchAppConfigContext mchAppConfigContext = configContextQueryService.queryMchInfoAndAppInfo(
        mchNo, appId);

    if (mchAppConfigContext == null) {
      throw new BizException("商户或商户应用不存在");
    }

    if (mchAppConfigContext.getMchInfo() == null
        || mchAppConfigContext.getMchInfo().getState() != CS.YES) {
      throw new BizException("商户信息不存在或商户状态不可用");
    }

    MchApp mchApp = mchAppConfigContext.getMchApp();
    if (mchApp == null || mchApp.getState() != CS.YES) {
      throw new BizException("商户应用不存在或应用状态不可用");
    }

    if (!mchApp.getMchNo().equals(mchNo)) {
      throw new BizException("参数appId与商户号不匹配");
    }

    // 验签
    String appSecret = mchApp.getAppSecret();

    // todo sign and verify
    // 转换为 JSON
//    JSONObject bizReqJSON = (JSONObject)JSONObject.toJSON(bizRQ);
//    bizReqJSON.remove("sign");
//    if(!sign.equalsIgnoreCase(JeepayKit.getSign(bizReqJSON, appSecret))){
//      throw new BizException("验签失败");
//    }

  }

}
