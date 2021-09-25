package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.requestDto.billPayments.AirtimeRequestDto;
import com.sabi.agent.core.dto.responseDto.ResponseDto;
import com.sabi.agent.service.integrations.BillPaymentService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
    public ResponseDto getBillCategories(@Validated @RequestParam(value = "direction")String direction,
                                                      @RequestParam(value = "fingerprint",required = true)String fingerprint,
                                                      @RequestParam(value = "page")Integer page,
                                                      @RequestParam(value = "size") Integer size,
                                                      @RequestParam(value = "sortBy") String sortBy) throws Exception{

        ResponseDto response = service.getBillCategories(direction, fingerprint, page, size, sortBy);

        return response;
    }

    @GetMapping("/{billCategoryId}")
    public ResponseDto getBillCategoryId(@Validated @PathVariable Integer billCategoryId,
                                                      @RequestParam(value = "fingerprint",required = true)String fingerprint) throws Exception{
        HttpStatus httpCode ;
        Response resp = new Response();
        ResponseDto response = service.getBillCategoryId(billCategoryId, fingerprint);
        return response;
    }



}
