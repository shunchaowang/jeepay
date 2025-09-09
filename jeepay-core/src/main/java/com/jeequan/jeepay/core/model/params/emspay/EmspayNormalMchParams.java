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

  private String env; // sandbox or production
  private String emsDirectUrl;
//  private String secretCode; // this will be in if config
//  private String rsaPublicKeyPem; // this will be in if config
//  private String bearerToken; // this will be in if config

  @Override
  public String deSenData() {
    return "";
  }
}
