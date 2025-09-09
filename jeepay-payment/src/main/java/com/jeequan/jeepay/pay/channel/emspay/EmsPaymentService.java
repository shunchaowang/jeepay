package com.jeequan.jeepay.pay.channel.emspay;

import com.jeequan.jeepay.core.entity.PayOrder;
import com.jeequan.jeepay.pay.channel.AbstractPaymentService;
import com.jeequan.jeepay.pay.model.MchAppConfigContext;
import com.jeequan.jeepay.pay.rqrs.AbstractRS;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import org.springframework.stereotype.Service;

@Service
public class EmsPaymentService extends AbstractPaymentService {

  @Override
  public String getIfCode() {
    return "";
  }

  @Override
  public boolean isSupport(String wayCode) {
    return false;
  }

  @Override
  public String preCheck(UnifiedOrderRQ bizRQ, PayOrder payOrder) {
    return "";
  }

  @Override
  public AbstractRS pay(UnifiedOrderRQ bizRQ, PayOrder payOrder,
      MchAppConfigContext mchAppConfigContext) throws Exception {
    return null;
  }
}
