package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.requestDto.billPayments.AirtimeRequestDto;
import com.sabi.agent.core.dto.requestDto.billPayments.BillCategoryRequestDTO;
import com.sabi.agent.core.dto.responseDto.billPayments.BillCategoryResponseDTO;
import com.sabi.agent.core.dto.responseDto.billPayments.BillerResponseDTO;
import com.sabi.agent.service.integrations.BillPaymentService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SuppressWarnings("All")
@RestController
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
    public ResponseEntity<Response> airtimePayment(@RequestBody AirtimeRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        AirtimeRequestDto response = service.airtimePayment(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/billCategories")
    public ResponseEntity<Response> getBillCategories(BillCategoryRequestDTO request){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<BillCategoryResponseDTO> response = service.getBillCategories(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/{billCategoryId}")
    public ResponseEntity<Response> getBillCategoryId(@PathVariable Integer billCategoryId,
                                                      @RequestParam(value = "fingerprint",required = true)String fingerprint){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<BillerResponseDTO> response = service.getBillCategoryId(billCategoryId, fingerprint);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



}
