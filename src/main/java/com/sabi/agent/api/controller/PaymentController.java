package com.sabi.agent.api.controller;

import com.sabi.framework.service.PaymentService;
import com.sabi.framework.utils.Constants;
import com.sabi.integrations.payment_integration.models.request.CheckOutRequest;
import com.sabi.integrations.payment_integration.models.response.CheckOutResponse;
import com.sabi.integrations.payment_integration.models.response.PaymentStatusResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.APP_CONTENT + "payment")
public class PaymentController {

    private final PaymentService  paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/checkout")
    public CheckOutResponse checkOut(@RequestBody @Valid CheckOutRequest checkOutRequest){
        return paymentService.checkOut(checkOutRequest);
    }

    @GetMapping("/checkStatus")
    public PaymentStatusResponse checkStatus(@RequestParam("paymentReference") String paymentReference){
        return paymentService.checkStatus(paymentReference);
    }
}
