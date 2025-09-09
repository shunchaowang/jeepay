package com.jeequan.jeepay.pay.emspay;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
public class EmspayRefundRequest {

  private String merchantReference;
  private Float amount; // number (6,2) 20.00
  private String metadata; // optional, eg refund reason
  private String env;
  private String sign;
}
