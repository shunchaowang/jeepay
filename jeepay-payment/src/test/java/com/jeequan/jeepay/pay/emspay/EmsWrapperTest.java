package com.jeequan.jeepay.pay.emspay;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jeequan.jeepay.core.constants.CS.IF_CODE;
import com.jeequan.jeepay.core.model.params.NormalMchParams;
import com.jeequan.jeepay.core.model.params.emspay.EmspayNormalMchParams;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class EmsWrapperTest {

  @Test
  void emspayNormalMchParamsTestSandbox() {
    var jsonStr = "{\"sandbox\":1, \"secret\":\"123456\",\"bearer\":\"456789\"}";
    NormalMchParams normalMchParams = NormalMchParams.factory(IF_CODE.EMSPAY, jsonStr);
    EmspayNormalMchParams emspayNormalMchParams = (EmspayNormalMchParams) normalMchParams;

    EmsWrapper emsWrapper = EmsWrapper.buildEmsWrapper(emspayNormalMchParams);
    log.info(emsWrapper.toString());
    assertEquals("sandbox", emsWrapper.getEnv());
    assertEquals("123456", emsWrapper.getSecretCode());
    assertEquals("456789", emsWrapper.getBearerToken());
  }

  @Test
  void emspayNormalMchParamsTestProduction() {
    var jsonStr = "{\"sandbox\":0, \"secret\":\"123456\",\"bearer\":\"456789\"}";
    NormalMchParams normalMchParams = NormalMchParams.factory(IF_CODE.EMSPAY, jsonStr);
    EmspayNormalMchParams emspayNormalMchParams = (EmspayNormalMchParams) normalMchParams;

    EmsWrapper emsWrapper = EmsWrapper.buildEmsWrapper(emspayNormalMchParams);
    log.info(emsWrapper.toString());
    assertEquals("production", emsWrapper.getEnv());
    assertEquals("123456", emsWrapper.getSecretCode());
    assertEquals("456789", emsWrapper.getBearerToken());
  }
}