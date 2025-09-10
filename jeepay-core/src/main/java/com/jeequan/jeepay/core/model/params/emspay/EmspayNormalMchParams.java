package com.jeequan.jeepay.core.model.params.emspay;

import com.jeequan.jeepay.core.model.params.NormalMchParams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class EmspayNormalMchParams extends NormalMchParams {

  private Byte sandbox; // sandbox or production
  private String secret;
  private String bearer;

  @Override
  public String deSenData() {
    return "";
  }

  public static final String CHECKOUT = "checkout";
  public static final String PAYMENT_QUERY = "payment-query";
  public static final String REFUND = "refund";
  public static final String REFUND_QUERY = "refund-query";
}
