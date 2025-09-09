package com.jeequan.jeepay.pay.emspay;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
public class EmspayPaymentQueryResponse {

  private String type; // eg sale
  private String merchantReference;
  private String requestReference;
  private String status; // 1 mean successful
  private String amount;
  private String currency;
  private String createdTime;
  private String completedTime;
}
