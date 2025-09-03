package com.jeequan.jeepay.pay.rqrs.payorder.payway.icbc;

import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRS;
import lombok.Data;

@Data
public class IcbcAppOrderRS extends UnifiedOrderRS {

  private String payData;

}
