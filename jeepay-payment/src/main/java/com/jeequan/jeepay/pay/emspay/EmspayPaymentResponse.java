package com.jeequan.jeepay.pay.emspay;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
public class EmspayPaymentResponse {

  private String amount; // two decimal 100.00
  private String currency; // USD
  private String merchantReference;
  private String requestReference;
  private String status; // 1 mean successful
  private String sign;
}
