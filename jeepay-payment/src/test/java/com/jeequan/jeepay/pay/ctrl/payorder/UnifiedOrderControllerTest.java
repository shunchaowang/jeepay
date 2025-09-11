package com.jeequan.jeepay.pay.ctrl.payorder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeequan.jeepay.pay.bootstrap.JeepayPayApplication;
import com.jeequan.jeepay.pay.emspay.EmsDirectOrderRQ;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {JeepayPayApplication.class})
class UnifiedOrderControllerTest {

//  @MockBean
//  private PayWayService payWayService;
//
//  @MockBean
//  private ConfigContextQueryService configContextQueryService;

//  @InjectMocks
//  private UnifiedOrderController unifiedOrderController;

  @Autowired
  private MockMvc mockMvc;

  @Test
  @Disabled
  void testPayEmsDirect() throws Exception {
    /**
     *   Map<String, Object> paramsMap = new HashMap<>();
     *         paramsMap.put("mchNo", "M1682391685");                   // 商户号
     *         paramsMap.put("appId", "6447428682ca7458118af79f");      // 应用ID
     *         paramsMap.put("mchOrderNo", "mho1694051705945");         // 商户订单号
     *         paramsMap.put("wayCode", "ALI_BAR");                     // 支付方式
     *         paramsMap.put("amount", 1L);                             // 金额，单位分
     *         paramsMap.put("currency", "CNY");                        // 币种，目前只支持cny
     *         paramsMap.put("clientIp", "192.166.1.132");              // 发起支付请求客户端的IP地址
     *         paramsMap.put("subject", "商品标题");                     // 商品标题
     *         paramsMap.put("body", "商品描述");                        // 商品描述
     *         paramsMap.put("notifyUrl", "https://www.jeequan.com");   // 异步通知地址
     *         paramsMap.put("reqTime", "1694051706");                  // 请求时间
     *         paramsMap.put("version", "1.0");                         // 接口版本号，固定：1.0
     *         paramsMap.put("signType", "MD5");                        // 签名类型
     *         paramsMap.put("channelExtra", "{\"authCode\":\"284957415846666792\"}");  // 渠道参数
     */
    EmsDirectOrderRQ request = new EmsDirectOrderRQ();
    request.setSubject("Subject");
    request.setBody("Body");
    request.setSignType("MD5");
    request.setVersion("1.0");
    request.setCardExpiryMonth("12");
    request.setCardExpiryYear("28");
    request.setCvv("666");
    request.setCustomerAddress("address");
    request.setCardNumber("1111222233334444");
    request.setCardHolderName("name");
    request.setCustomerCountry("country");
    request.setCustomerEmail("email@email.com");
    request.setCustomerPhone("13911189012");
    request.setAmount(12L);
    request.setCurrency("USD");
    request.setClientIp("clientIp");
    request.setAppId("appId");
    request.setMchNo("merchantId");
    request.setMchOrderNo("merchOrderNo");
    request.setNotifyUrl("notifyUrl");
    request.setReturnUrl("returnUrl");
    String channelExtra = "{\"name\":\"ems\", \"otherParam\":\"other\"}";
    request.setChannelExtra(channelExtra);
    request.setSign("sign");
    request.setWayCode("EMS_DIRECT");
    request.setReqTime("20251010");
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(request);
    mockMvc.perform(post("/api/pay/unifiedOrder")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(json))
        .andExpect(status().isOk());
  }
}