package com.jeequan.jeepay.pay.channel.emspay.payway;

import com.jeequan.jeepay.core.entity.PayOrder;
import com.jeequan.jeepay.pay.channel.emspay.EmspayPaymentService;
import com.jeequan.jeepay.pay.config.SystemYmlConfig;
import com.jeequan.jeepay.pay.emspay.EmsDirectOrderRQ;
import com.jeequan.jeepay.pay.emspay.EmsDirectOrderRS;
import com.jeequan.jeepay.pay.emspay.EmsWrapper;
import com.jeequan.jeepay.pay.emspay.EmspayPaymentRequest;
import com.jeequan.jeepay.pay.emspay.EmspayPaymentResponse;
import com.jeequan.jeepay.pay.model.MchAppConfigContext;
import com.jeequan.jeepay.pay.rqrs.AbstractRS;
import com.jeequan.jeepay.pay.rqrs.msg.ChannelRetMsg;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import com.jeequan.jeepay.pay.util.ApiResBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

//@Service("emspayPaymentByEmsDirectService")
@Service
@Slf4j
public class EmsDirect extends EmspayPaymentService {

  private final RestClient restClient;
  private final String emsPublicKeyBase64;
  private final String baseUrl;

  public EmsDirect(SystemYmlConfig config) {
    restClient = RestClient.builder()
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).build();
    emsPublicKeyBase64 = config.getEmsPublicKey();
    baseUrl = config.getEmsUrl();
  }

  @Override
  public String preCheck(UnifiedOrderRQ bizRQ, PayOrder payOrder) {
    return null;
  }

  @Override
  public AbstractRS pay(UnifiedOrderRQ bizRQ, PayOrder payOrder,
      MchAppConfigContext mchAppConfigContext) throws Exception {

    EmsDirectOrderRQ rq = (EmsDirectOrderRQ) bizRQ;
    EmspayPaymentRequest request = EmspayPaymentRequest.build(rq);

    EmsWrapper emsWrapper = configContextQueryService.getEmsWrapper(mchAppConfigContext);
    request.setEnv(emsWrapper.getEnv());

    // todo encrypt cardNumber, cardExpiryMonth, cardExpiryYear, CVV with public key

    // todo add the signature
    /**
     * php
     * // 1. Sort parameters alphabetically
     * ksort($params);
     *
     * // 2. Build a query string
     * $signingString = http_build_query($params);
     *
     * // 3. Append your secret code
     * $secretCode = 'YOUR_SECRET_CODE';
     * $signingString .= $secretCode;
     *
     * // 4. SHA‑512 hash
     * $signature = hash('SHA512', $signingString);
     *
     * // 5. Add to payload
     * $params['sign'] = $signature;
     */

    RestClient restClient = RestClient.builder().build();

    EmspayPaymentResponse response = restClient.post()
        .uri(baseUrl + "/payment")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + emsPublicKeyBase64)
        .body(request)
        .retrieve()
        .body(EmspayPaymentResponse.class);

    EmsDirectOrderRS res = ApiResBuilder.buildSuccess(EmsDirectOrderRS.class);
    ChannelRetMsg channelRetMsg = new ChannelRetMsg();
    res.setChannelRetMsg(channelRetMsg);
    return res;
//    return super.pay(bizRQ, payOrder, mchAppConfigContext);
  }
}
