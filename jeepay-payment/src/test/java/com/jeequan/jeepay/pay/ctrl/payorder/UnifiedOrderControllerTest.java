package com.jeequan.jeepay.pay.ctrl.payorder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jeequan.jeepay.pay.bootstrap.JeepayPayApplication;
import com.jeequan.jeepay.pay.service.ConfigContextQueryService;
import com.jeequan.jeepay.service.impl.PayWayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {JeepayPayApplication.class})
class UnifiedOrderControllerTest {

//  @MockBean
//  private PayWayService payWayService;
//
//  @MockBean
//  private ConfigContextQueryService configContextQueryService;

//  @InjectMocks
//  private UnifiedOrderController unifiedOrderController;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testPayOrder() throws Exception {
    mockMvc.perform(post("/api/pay/unifiedOrder")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .contentType(""))
        .andExpect(status().isOk());
  }
}