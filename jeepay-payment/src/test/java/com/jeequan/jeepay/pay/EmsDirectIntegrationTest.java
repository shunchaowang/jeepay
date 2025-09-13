package com.jeequan.jeepay.pay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeequan.jeepay.pay.emspay.EmsDirectOrderRQ;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@AutoConfigureMockMvc
//@ContextConfiguration(classes = {JeepayPayApplication.class})
class EmsDirectIntegrationTest {

//  @MockBean
//  private PayWayService payWayService;
//
//  @MockBean
//  private ConfigContextQueryService configContextQueryService;

//  @InjectMocks
//  private UnifiedOrderController unifiedOrderController;

//  @Autowired
//  private MockMvc mockMvc;

  @Test
//  @Disabled
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

    String baseUrl = "http://localhost:9216";
    String secretCode = "secret";
    String mchNo = "M1756666603";
    String appId = "68bc9ca868d00aa3cd7d66e4";
    String mchOrderNo = "11111111";
    Long amount = 12L;
    String currency = "CNY";
    String clientId = "192.166.1.132";
    String subject = "subject";
    String body = "body";
    String notifyUrl = "http://localhost/notify";
    String returnUrl = "http://localhost/return";
    String reqTime = "1694051706";
    String version = "1.0";
    String signType = "MD5";
    String wayCode = "EMS_DIRECT";
//    String channelExtra = "{\"cardExpiryMonth\":\"12\", \"cardExpiryYear\":\"30\", \"cvv\":\"666\"}";

    String channelExtra = """
        {
          "cardExpiryMonth": "12",
          "cardExpiryYear": "30",
          "cvv": "666",
          "cardNumber": "1111222233334444",
          "cardHolderName": "name",
          "customerEmail": "email@email.com",
          "customerPhone": "1238785432",
          "customerAddress": "Sesame St",
          "customerCountry": "US",
          "sign": "sign"
        }
        """;

    EmsDirectOrderRQ request = new EmsDirectOrderRQ();
    request.setSubject(subject);
    request.setBody(body);
    request.setSignType(signType);
    request.setVersion(version);
//    request.setCardExpiryMonth("12");
//    request.setCardExpiryYear("28");
//    request.setCvv("666");
//    request.setCustomerAddress("address");
//    request.setCardNumber("1111222233334444");
//    request.setCardHolderName("name");
//    request.setCustomerCountry("country");
//    request.setCustomerEmail("email@email.com");
//    request.setCustomerPhone("13911189012");
//    request.setSign("sign");
    request.setAmount(amount);
    request.setCurrency(currency);
    request.setClientIp(clientId);
    request.setAppId(appId);
    request.setMchNo(mchNo);
    request.setMchOrderNo(mchOrderNo);
    request.setNotifyUrl(notifyUrl);
    request.setReturnUrl(returnUrl);
    request.setWayCode(wayCode);
    request.setReqTime(reqTime);
    request.setChannelExtra(channelExtra);
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(request);
//    mockMvc.perform(post("/api/pay/unifiedOrder")
//            .contentType(MediaType.APPLICATION_JSON_UTF8)
//            .content(json))
//        .andExpect(status().isOk());

    RestClient restClient = RestClient.builder().build();

    ResponseEntity<Void> response = restClient.post()
        .uri(baseUrl + "/api/pay/emsDirectOrder")
//        .uri(baseUrl + "/api/pay/unifiedOrder")
        .contentType(MediaType.APPLICATION_JSON)
        .body(request)
        .retrieve()
        .toBodilessEntity();

  }
}