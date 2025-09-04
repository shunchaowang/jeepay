package com.jeequan.jeepay.pay.rqrs.payorder.payway;

import com.jeequan.jeepay.core.constants.CS.PAY_WAY_CODE;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import lombok.Data;

@Data
public class EmsCcOrderRQ extends UnifiedOrderRQ {

  public EmsCcOrderRQ() {
    this.setWayCode(PAY_WAY_CODE.EMS_CC);
  }
}
