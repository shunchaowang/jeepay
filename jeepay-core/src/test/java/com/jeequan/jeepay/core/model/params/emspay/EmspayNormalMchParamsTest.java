package com.jeequan.jeepay.core.model.params.emspay;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jeequan.jeepay.core.constants.CS.IF_CODE;
import com.jeequan.jeepay.core.model.params.NormalMchParams;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class EmspayNormalMchParamsTest {

  EmspayNormalMchParams emspayNormalMchParams;

  @Test
  void testParseJson() {
    var jsonStr = "{\"sandbox\":1, \"secret\":\"123456\",\"bearer\":\"456789\"}";
    NormalMchParams normalMchParams = NormalMchParams.factory(IF_CODE.EMSPAY, jsonStr);
    EmspayNormalMchParams emspayNormalMchParams = (EmspayNormalMchParams) normalMchParams;
    log.info(emspayNormalMchParams.toString());

    assertEquals(emspayNormalMchParams.getSandbox(), Byte.valueOf("1"));
    assertEquals("123456", emspayNormalMchParams.getSecret());
    assertEquals("456789", emspayNormalMchParams.getBearer());
  }
}