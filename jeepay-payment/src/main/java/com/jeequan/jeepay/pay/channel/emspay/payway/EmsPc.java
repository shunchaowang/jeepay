package com.jeequan.jeepay.pay.channel.emspay.payway;

import com.jeequan.jeepay.core.entity.PayOrder;
import com.jeequan.jeepay.pay.channel.emspay.EmsOrderRequest;
import com.jeequan.jeepay.pay.channel.emspay.EmspayPaymentService;
import com.jeequan.jeepay.pay.model.MchAppConfigContext;
import com.jeequan.jeepay.pay.rqrs.AbstractRS;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import com.jeequan.jeepay.pay.rqrs.payorder.payway.EmsOrderRQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("emspayPaymentByEMSPCService")
public class EmsPc extends EmspayPaymentService {

  @Override
  public AbstractRS pay(UnifiedOrderRQ bizRQ, PayOrder payOrder,
      MchAppConfigContext mchAppConfigContext) throws Exception {

    EmsOrderRQ request = (EmsOrderRQ) bizRQ;

    EmsOrderRequest emsOrderRequest = EmsOrderRequest.builder()
        .amount(String.valueOf(request.getAmount()))
        .currency(request.getCurrency())
        .merchantReference(request.getMchOrderNo())
        .notifyUrl(request.getNotifyUrl())
        .returnUrl(request.getReturnUrl())
        .customerAddress("customer address")
        .customerCountry("customer country")
        .customerEmail("customer email")
        .customerIp("customer ip")
        .customerPhone("customer phone")
        .cardHolderName("card holder name")
        .cardNumber("card number")

        .build();

    return super.pay(bizRQ, payOrder, mchAppConfigContext);
  }
}
