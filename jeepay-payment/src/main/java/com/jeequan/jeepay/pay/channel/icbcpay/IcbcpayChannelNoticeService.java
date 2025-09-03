package com.jeequan.jeepay.pay.channel.icbcpay;

import com.alibaba.fastjson.JSONObject;
import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.core.constants.CS.IF_CODE;
import com.jeequan.jeepay.core.entity.PayOrder;
import com.jeequan.jeepay.core.exception.ResponseException;
import com.jeequan.jeepay.core.model.params.plspay.PlspayConfig;
import com.jeequan.jeepay.core.model.params.plspay.PlspayNormalMchParams;
import com.jeequan.jeepay.pay.channel.AbstractChannelNoticeService;
import com.jeequan.jeepay.pay.model.MchAppConfigContext;
import com.jeequan.jeepay.pay.rqrs.msg.ChannelRetMsg;
import com.jeequan.jeepay.util.JeepayKit;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IcbcpayChannelNoticeService extends AbstractChannelNoticeService {


  @Override
  public String getIfCode() {
    return IF_CODE.ICBCPAY;
  }

  @Override
  public MutablePair<String, Object> parseParams(HttpServletRequest request, String urlOrderId,
      NoticeTypeEnum noticeTypeEnum) {
    try {
      JSONObject params = getReqParamJSON();
      // 获取订单号
      String payOrderId = params.getString("mchOrderNo");
      return MutablePair.of(payOrderId, params);
    } catch (Exception e) {
      log.error("error", e);
      throw ResponseException.buildText("ERROR");
    }
  }

  @Override
  public ChannelRetMsg doNotice(HttpServletRequest request, Object params, PayOrder payOrder,
      MchAppConfigContext mchAppConfigContext, NoticeTypeEnum noticeTypeEnum) {
    try {
      String logPrefix = "【处理ICBC回调】";
      // 获取请求参数
      JSONObject paramsJson = (JSONObject) params;
      log.info("{} 回调参数, jsonParams：{}", logPrefix, paramsJson);
      // 校验签名
      String sign = paramsJson.getString("sign");
      boolean verifyResult = verifyParams(paramsJson, sign, mchAppConfigContext);
      // 验证参数失败
      if (!verifyResult) {
        throw ResponseException.buildText("ERROR");
      }
      log.info("{}验证支付通知数据及签名通过", logPrefix);
      // 验签成功后判断上游订单状态
      ResponseEntity okResponse = textResp("success");

      ChannelRetMsg result = new ChannelRetMsg();
      result.setResponseEntity(okResponse);
      result.setChannelOrderId(paramsJson.getString("payOrderId"));
      result.setChannelState(ChannelRetMsg.ChannelState.CONFIRM_SUCCESS);
      return result;
    } catch (Exception e) {
      log.error("error", e);
      throw ResponseException.buildText("ERROR");
    }
  }

  /**
   * 验证ICBC通知参数
   *
   * @return boolean  true or false
   */
  private boolean verifyParams(JSONObject jsonParams, String sign, MchAppConfigContext mchAppConfigContext) {
    try {
      // 返回数据
      if (StringUtils.isEmpty(sign)) {
        log.info("验签参数为空 [sign] :{}", sign);
        return false;
      }
      PlspayNormalMchParams normalMchParams = (PlspayNormalMchParams) configContextQueryService.queryNormalMchParams(mchAppConfigContext.getMchNo(), mchAppConfigContext.getAppId(), CS.IF_CODE.PLSPAY);
      jsonParams.remove("sign");
      // 获取md5秘钥,生成签名
      String newSign = JeepayKit.getSign(jsonParams, normalMchParams.getAppSecret());
      // 验签  异步时都是MD5
      if (!sign.equals(newSign)) {
        log.info("验签失败！ 回调参数：parameter = {}", jsonParams);
        return false;
      }
      // 支付状态: 0-订单生成, 1-支付中, 2-支付成功, 3-支付失败, 4-已撤销, 5-已退款, 6-订单关闭
      String status = jsonParams.getString("state");
      if (!PlspayConfig.PAY_STATE_SUCCESS.equals(status)) {
        log.info("订单状态错误！ state = {}", status);
        return false;
      }
      return true;
    } catch (Exception e) {
      log.error("error", e);
      throw ResponseException.buildText("ERROR");
    }
  }
}
