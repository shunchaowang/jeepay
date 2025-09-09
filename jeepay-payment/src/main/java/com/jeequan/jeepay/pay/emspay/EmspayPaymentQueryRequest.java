package com.jeequan.jeepay.pay.emspay;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
public class EmspayPaymentQueryRequest {

  private String merchantReference;
  private String env;
  private String sign;
}
