package com.sabi.agent.api.controller;


import  com.sabi.agent.core.dto.requestDto.billPayments.AirtimeRequestDto;
import com.sabi.agent.core.dto.responseDto.ResponseDto;
import com.sabi.agent.core.dto.responseDto.billPayments.AirtimeResponseDto;
import com.sabi.agent.core.dto.responseDto.billPayments.BillPaymentResponseDto;
import com.sabi.agent.core.integrations.response.MerchantProductCategory;
import com.sabi.agent.core.models.billPayments.Airtime;
import com.sabi.agent.service.integrations.BillPaymentService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;


@SuppressWarnings("All")
@RestController
@Validated
@Valid
@RequestMapping(Constants.APP_CONTENT +"billpayments")
public class BillPaymentController {

    private final BillPaymentService service;

    public BillPaymentController(BillPaymentService service) {
        this.service = service;
    }

    /** <summary>
     *  Bill Payment creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for Bills Payment</remarks>
     */

    @PostMapping("/airtime")
    public ResponseEntity<Response> airtimePayment(@Validated @RequestBody AirtimeRequestDto request){
        System.err.println(request);
        HttpStatus httpCode ;
        Response resp = new Response();
        AirtimeResponseDto response = service.airtimePayment(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PutMapping("/airtimeStatus")
    public ResponseEntity<Response> airtimeStatus(@RequestHeader("fingerPrint") String fingerPrint,
                                                  @RequestParam("billPurchaseId") String purchaseId){
        HttpStatus httpCode ;
        Response resp = new Response();
        Airtime response = service.airtimeStatus(
                purchaseId, fingerPrint);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/billersPerCategories")
    public BillPaymentResponseDto getBillersPerCategories() throws Exception{
        return service.getBillersPerCategories();
    }

    @GetMapping("/billers")
    public ResponseDto getAllBills( @RequestParam(value = "direction",required = false)String direction,
                                    @RequestParam(value = "page",required = false)Integer page,
                                    @RequestParam(value = "size",required = false)Integer size,
                                    @RequestParam(value = "sortBy",required = false)String sortBy) throws Exception{
        HttpStatus httpCode ;
        Response resp = new Response();
        ResponseDto response = service.getBillers(direction, page, size, sortBy);
        return response;
    }

}
