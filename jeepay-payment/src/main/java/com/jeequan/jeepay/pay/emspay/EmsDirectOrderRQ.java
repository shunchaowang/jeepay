package com.jeequan.jeepay.pay.emspay;

import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import lombok.Data;

@Data
public class EmsDirectOrderRQ extends UnifiedOrderRQ {

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
