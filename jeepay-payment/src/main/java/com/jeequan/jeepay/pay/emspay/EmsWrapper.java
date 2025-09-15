package com.jeequan.jeepay.pay.emspay;

import com.jeequan.jeepay.core.model.params.emspay.EmspayNormalMchParams;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@Slf4j
public class EmsWrapper {

  private String env; // sandbox or production
  private String secretCode;
  private String bearerToken;

  public static EmsWrapper buildEmsWrapper(EmspayNormalMchParams emspayNormalMchParams) {
    return EmsWrapper.builder()
        .env(emspayNormalMchParams.getSandbox() == 1 ? "sandbox" : "production")
        .secretCode(emspayNormalMchParams.getSecret())
        .bearerToken(emspayNormalMchParams.getBearer())
        .build();
  }

  public static final String CHECKOUT = "checkout";
  public static final String PAYMENT_QUERY = "payment-query";
  public static final String REFUND = "refund";
  public static final String REFUND_QUERY = "refund-query";
}
