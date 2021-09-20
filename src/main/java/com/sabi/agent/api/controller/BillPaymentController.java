package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.requestDto.billPayments.AirtimeRequestDto;
import com.sabi.agent.core.dto.requestDto.billPayments.BillCategoryDTO;
import com.sabi.agent.core.dto.requestDto.billPayments.BillerDTO;
import com.sabi.agent.service.services.billPayments.BillPaymentService;
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
    public ResponseEntity<Response> airtimePayment(@RequestParam(value = "billerId",required = true)Long billerId,
                                            @RequestParam(value = "denomination")String denomination,
                                          @RequestParam(value = "msisdn",required = true)String msisdn,
                                            @RequestParam(value = "fingerprint",required = true)String fingerprint){
        HttpStatus httpCode ;
        Response resp = new Response();
        AirtimeRequestDto response = service.airtimePayment(billerId, fingerprint, denomination, msisdn);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/billCategories")
    public ResponseEntity<Response> getBillCategories(@RequestParam(value = "direction")String direction,
                                                      @RequestParam(value = "page")Integer page,
                                                      @RequestParam(value = "size")Integer size,
                                                      @RequestParam(value = "sortBy")String sortBy,
                                                      @RequestParam(value = "fingerprint",required = true)String fingerprint){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<BillCategoryDTO> response = service.getBillCategories(direction, fingerprint, page, size, sortBy);
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
        List<BillerDTO> response = service.getBillCategoryId(billCategoryId, fingerprint);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



}
