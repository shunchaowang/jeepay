package com.jeequan.jeepay.pay.rqrs.payorder.payway.icbc;

import com.jeequan.jeepay.core.constants.CS.PAY_WAY_CODE;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import lombok.Data;

@Data
public class IcbcAppOrderRQ extends UnifiedOrderRQ {

  public IcbcAppOrderRQ() {
    this.setWayCode(PAY_WAY_CODE.ICBC_APP);
  }
}
