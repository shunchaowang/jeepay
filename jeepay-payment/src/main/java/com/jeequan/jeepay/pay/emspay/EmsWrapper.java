package com.jeequan.jeepay.pay.emspay;

import com.jeequan.jeepay.core.model.params.emspay.EmspayNormalMchParams;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

@Data
@Builder
@Slf4j
public class EmsWrapper {

  private RestClient restClient;
  private String env; // sandbox or production
//  private String secretCode; // this will be in if config
//  private String rsaPublicKeyPem; // this will be in if config
//  private String bearerToken; // this will be in if config

  public static EmsWrapper buildEmsWrapper(EmspayNormalMchParams emspayNormalMchParams) {
    return EmsWrapper.builder()
        .restClient(RestClient.builder().baseUrl(emspayNormalMchParams.getEmsDirectUrl()).build())
        .env(emspayNormalMchParams.getEnv())
        .build();
  }
}
