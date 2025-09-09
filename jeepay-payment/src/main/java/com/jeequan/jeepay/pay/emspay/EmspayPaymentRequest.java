package com.jeequan.jeepay.pay.emspay;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
public class EmspayPaymentRequest {

  private String amount; // two decimal 100.00
  private String currency; // USD
  private String merchantReference;
  private String notifyUrl;
  private String returnUrl;
  private String customerAddress;
  private String customerCountry;
  private String customerEmail;
  private String customerIp;
  private String customerPhone;
  private String cardHolderName;
  private String cardNumber; //Encrypted via RSA
  private String cardExpiryMonth; // Encrypted via RSA
  private String cardExpiryYear; // Encrypted via RSA
  private String cvv;
  private String siteId; // your website uuid
  private String env; // sandbox or production
  private String sign; // sha512 signature (includes encrypted card fields
}
