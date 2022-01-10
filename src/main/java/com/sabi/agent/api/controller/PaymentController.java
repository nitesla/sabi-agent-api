package com.sabi.agent.api.controller;

import com.sabi.agent.core.dto.requestDto.CardTokenizationRequest;
import com.sabi.agent.core.models.agentModel.AgentCard;
import com.sabi.agent.service.services.AgentCardService;
import com.sabi.framework.integrations.payment_integration.models.request.CardPaymentRequest;
import com.sabi.framework.integrations.payment_integration.models.request.CheckOutRequest;
import com.sabi.framework.integrations.payment_integration.models.request.VerveOtpRequest;
import com.sabi.framework.integrations.payment_integration.models.response.CardPaymentResponse;
import com.sabi.framework.integrations.payment_integration.models.response.CheckOutResponse;
import com.sabi.framework.integrations.payment_integration.models.response.PaymentStatusResponse;
import com.sabi.framework.service.PaymentService;
import com.sabi.framework.utils.Constants;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.APP_CONTENT + "payment")
public class PaymentController {

    private final PaymentService  paymentService;
    private final AgentCardService agentCardService;

    public PaymentController(PaymentService paymentService, AgentCardService agentCardService) {
        this.paymentService = paymentService;
        this.agentCardService = agentCardService;
    }

    @PostMapping("/checkout")
    public CheckOutResponse checkOut(@RequestBody @Valid CheckOutRequest checkOutRequest){
        return paymentService.checkOut(checkOutRequest);
    }

    @GetMapping("/checkStatus")
    public PaymentStatusResponse checkStatus(@RequestParam("paymentReference") String paymentReference){
        return paymentService.checkStatus(paymentReference);
    }

    @PostMapping("/payWithCard")
    public CardPaymentResponse payWithCard(@RequestBody  @Valid CardPaymentRequest cardPaymentRequest){
        return paymentService.payWithCard(cardPaymentRequest);
    }

    @PostMapping("/validateOtp")
    public CardPaymentResponse validateOtp(@RequestBody @Valid VerveOtpRequest verveOtpRequest){
        return paymentService.confirmVerveOtp(verveOtpRequest);
    }
}
