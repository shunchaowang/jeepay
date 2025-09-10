package com.jeequan.jeepay.pay.emspay;

import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import lombok.Data;

/**
 * 第一步：
 * 设所有发送或者接收到的数据为集合M，将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序），使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串stringA。
 * 特别注意以下重要规则： ◆ 参数名ASCII码从小到大排序（字典序）； ◆ 如果参数的值为空不参与签名； ◆ 参数名区分大小写； ◆
 * 验证调用返回或支付中心主动通知签名时，传送的sign参数不参与签名，将生成的签名与该sign值作校验。 ◆ 支付中心接口可能增加字段，验证签名时必须支持增加的扩展字段
 * <p>
 * 第二步： 在stringA最后拼接上key[即 StringA +"&key=" + 私钥 ]
 * 得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。 如请求支付系统参数如下： >
 * 运营管理平台可以管理商户的私钥
 * <p>
 * 提示：签名以及接口调用，请使用jeepay官方提供的sdk：https://gitee.com/jeequan/jeepay-sdk-java
 */
@Data
public class EmsDirectOrderRQ extends UnifiedOrderRQ {

  /*
  如请求支付系统参数如下：

        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("mchNo", "M1682391685");                   // 商户号
        paramsMap.put("appId", "6447428682ca7458118af79f");      // 应用ID
        paramsMap.put("mchOrderNo", "mho1694051705945");         // 商户订单号
        paramsMap.put("wayCode", "ALI_BAR");                     // 支付方式
        paramsMap.put("amount", 1L);                             // 金额，单位分
        paramsMap.put("currency", "CNY");                        // 币种，目前只支持cny
        paramsMap.put("clientIp", "192.166.1.132");              // 发起支付请求客户端的IP地址
        paramsMap.put("subject", "商品标题");                     // 商品标题
        paramsMap.put("body", "商品描述");                        // 商品描述
        paramsMap.put("notifyUrl", "https://www.jeequan.com");   // 异步通知地址
        paramsMap.put("reqTime", "1694051706");                  // 请求时间
        paramsMap.put("version", "1.0");                         // 接口版本号，固定：1.0
        paramsMap.put("signType", "MD5");                        // 签名类型
        paramsMap.put("channelExtra", "{\"authCode\":\"284957415846666792\"}");  // 渠道参数
待签名值：
amount=1&appId=6447428682ca7458118af79f&body=商品描述&channelExtra={“authCode”:”284957415846666792”}&clientIp=192.166.1.132&currency=CNY&mchNo=M1682391685&mchOrderNo=mho1694051705945&notifyUrl=https://www.jeequan.com&reqTime=1694051706&signType=MD5&subject=商品标题&version=1.0&wayCode=ALI_BAR&key=UNpEETkvMpqC9oDLBr9S2X7U92k462h3zhHiy7hj4xbw23PiWhMv6TCAQ2vh8PzynZXZYo9n6puxHkAHG7li6LZi8IpaQrshzydnBll64iKlb4U59ggiyCTaHJeqffiW

签名结果：924065BA077FA461A9B06D2E76E9ED3C

最终请求支付系统参数：
{“amount”:1,”mchOrderNo”:”mho1694051705945”,”subject”:”商品标题”,”wayCode”:”ALI_BAR”,”sign”:”924065BA077FA461A9B06D2E76E9ED3C”,”reqTime”:”1694051706”,”body”:”商品描述”,”version”:”1.0”,”channelExtra”:”{"authCode":"284957415846666792"}”,”appId”:”6447428682ca7458118af79f”,”clientIp”:”192.166.1.132”,”notifyUrl”:”https://www.jeequan.com","signType":"MD5","currency":"CNY","mchNo":"M1682391685"}

> 运营管理平台可以管理商户的私钥

提示：签名以及接口调用，请使用jeepay官方提供的sdk：https://gitee.com/jeequan/jeepay-sdk-java


  */

  // these fields inherit from the unified order rq: amount (convert from long to string), currency,
  // clientId, notifyUrl, returnUrl.
  // merchantReference is from mchOrderNo of UnifiedOrderRQ
  // customerIp is from clientId
  // all other fields are in the channelExtra params in json format
  // {customerAddress: , customerCountry: , customerEmail: , customerPhone:, cardHolderName:,
  // cardNumber:,cardExpiryMonth:,cardExpiryYear, cvv:}
  // siteId will be from the merch setting, env from the code, sign will be computed before sending to ems
  private String customerAddress; // billing street address
  private String customerCountry;
  private String customerEmail;
  private String customerPhone;
  private String cardHolderName;
  private String cardNumber; // encrypted via rsa
  private String cardExpiryMonth; // encrypted via rsa
  private String cardExpiryYear; // encrypted via rsa
  private String cvv; // encrypted via rsa

}
