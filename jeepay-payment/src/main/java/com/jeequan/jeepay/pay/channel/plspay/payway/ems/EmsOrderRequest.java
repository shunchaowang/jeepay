package com.jeequan.jeepay.pay.channel.plspay.payway.ems;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmsOrderRequest {
  private String amount; // two decimal eg 100.00
  private String currency; // currency code eg USD
  private String merchantReference; // random uuid per request
  private String notifyUrl;
  private String returnUrl;
  private String customerAddress; // billing street address
  private String customerCountry;
  private String customerEmail;
  private String customerIp;
  private String customerPhone;
  private String cardHolderName;
  private String cardNumber; // encrypted via rsa
  private String cardExpiryMonth; // encrypted via rsa
  private String cardExpiryYear; // encrypted via rsa
  private String cvv; // encrypted via rsa
  private String siteId; // website uuid
  private String env; // sandbox or production
  private String sign; // sha512 signature including encrypted card fields

}
