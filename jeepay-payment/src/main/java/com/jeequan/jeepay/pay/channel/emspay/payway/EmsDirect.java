package com.jeequan.jeepay.pay.channel.emspay.payway;

import com.jeequan.jeepay.pay.channel.emspay.EmsPaymentService;
import com.jeequan.jeepay.pay.emspay.EmspayPaymentQueryRequest;
import com.jeequan.jeepay.pay.emspay.EmspayPaymentQueryResponse;
import com.jeequan.jeepay.pay.emspay.EmspayPaymentRequest;
import com.jeequan.jeepay.pay.emspay.EmspayPaymentResponse;
import com.jeequan.jeepay.pay.emspay.EmspayRefundQueryRequest;
import com.jeequan.jeepay.pay.emspay.EmspayRefundQueryResponse;
import com.jeequan.jeepay.pay.emspay.EmspayRefundRequest;
import com.jeequan.jeepay.pay.emspay.EmspayRefundResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmsDirect extends EmsPaymentService {

  public EmspayPaymentResponse pay(EmspayPaymentRequest request) {
    return null;
  }

  public EmspayPaymentQueryResponse queryPayment(EmspayPaymentQueryRequest request) {
    return null;
  }

  public EmspayRefundResponse refund(EmspayRefundRequest request) {

    return null;
  }

  public EmspayRefundQueryResponse queryRefund(EmspayRefundQueryRequest request) {
    return null;
  }



}
