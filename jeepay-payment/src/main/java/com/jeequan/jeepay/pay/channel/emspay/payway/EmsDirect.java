package com.jeequan.jeepay.pay.channel.emspay.payway;

import com.jeequan.jeepay.core.entity.PayOrder;
import com.jeequan.jeepay.pay.channel.emspay.EmsPaymentService;
import com.jeequan.jeepay.pay.config.SystemYmlConfig;
import com.jeequan.jeepay.pay.emspay.EmsDirectOrderRQ;
import com.jeequan.jeepay.pay.emspay.EmsWrapper;
import com.jeequan.jeepay.pay.model.MchAppConfigContext;
import com.jeequan.jeepay.pay.rqrs.AbstractRS;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class EmsDirect extends EmsPaymentService {

  private final RestClient restClient;
  private final String emsPublicKeyBase64;

  public EmsDirect(SystemYmlConfig config) {
    restClient = RestClient.builder().baseUrl(config.getEmsUrl())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).build();
    emsPublicKeyBase64 = config.getEmsPublicKey();
  }


  @Override
  public AbstractRS pay(UnifiedOrderRQ bizRQ, PayOrder payOrder,
      MchAppConfigContext mchAppConfigContext) throws Exception {

    EmsDirectOrderRQ rq = (EmsDirectOrderRQ) bizRQ;

    EmsWrapper emsWrapper = configContextQueryService.getEmsWrapper(mchAppConfigContext);

    restClient.get();

    return super.pay(bizRQ, payOrder, mchAppConfigContext);
  }
}
