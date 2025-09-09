package com.jeequan.jeepay.pay.emspay;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
public class EmspayRefundResponse {

  private Request request;
  private Response response;
  private Payload payload;

  private static record Request(String id, String time) {

  }

  private static record Response(String code, String message, String time) {

  }

  private static class Payload {

    private String merchantReference;
    private String refundReference;
    private String refundAmount;
    private String status; // 4 is successful
    private Metadata metadata;

    private static record Metadata(String reason) {

    }
  }
}
