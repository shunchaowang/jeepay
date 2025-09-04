package com.jeequan.jeepay.pay.rqrs.payorder.payway;

import com.jeequan.jeepay.core.constants.CS.PAY_WAY_CODE;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import lombok.Data;

@Data
public class EmsOrderRQ extends UnifiedOrderRQ {

  public EmsOrderRQ() {
    this.setWayCode(PAY_WAY_CODE.EMS_PC);
  }
}
