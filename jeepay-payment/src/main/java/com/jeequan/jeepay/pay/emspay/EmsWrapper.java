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
  private String rsaPublicKeyPem;
  private String bearerToken;

  public static EmsWrapper buildEmsWrapper(EmspayNormalMchParams emspayNormalMchParams) {
    return EmsWrapper.builder()
        .env(emspayNormalMchParams.getEnv())
        .secretCode(emspayNormalMchParams.getSecretCode())
        .rsaPublicKeyPem(emspayNormalMchParams.getRsaPublicKeyPem())
        .bearerToken(emspayNormalMchParams.getBearerToken())
        .build();
  }
}
