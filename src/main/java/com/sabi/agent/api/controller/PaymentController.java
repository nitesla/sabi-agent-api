package com.sabi.agent.api.controller;


import com.sabi.agent.service.integrations.OrderService;
import com.sabi.agent.service.services.AgentCardService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.integrations.payment_integration.models.request.CardPaymentRequest;
import com.sabi.framework.integrations.payment_integration.models.request.CheckOutRequest;
import com.sabi.framework.integrations.payment_integration.models.request.VerveOtpRequest;
import com.sabi.framework.integrations.payment_integration.models.response.CardPaymentResponse;
import com.sabi.framework.integrations.payment_integration.models.response.CheckOutResponse;
import com.sabi.framework.integrations.payment_integration.models.response.PaymentStatusResponse;
import com.sabi.framework.service.PaymentService;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.APP_CONTENT + "payment")
public class PaymentController {

    private final PaymentService  paymentService;
    private final AgentCardService agentCardService;
    private final OrderService orderService;




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

    @GetMapping("/history")
    public ResponseEntity<Response> paymentHistory(@RequestParam(value = "agentId", required = false) Long orderId,
//                                                   @RequestParam(value = "sort", required = false) String sort,
                                                   @RequestParam(value = "page") Integer page,
                                                   @RequestParam(value = "pageSize") Integer pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
//        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
//                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        Page<Map> response = orderService.paymentHistory(orderId, page, pageSize);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);

    }

}
