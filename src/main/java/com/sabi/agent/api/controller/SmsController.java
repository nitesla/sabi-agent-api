package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.requestDto.SmsRequestDto;
import com.sabi.agent.service.services.SmsService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT +"testsms")
public class SmsController {

    private final SmsService service;

    public SmsController(SmsService service) {
        this.service = service;
    }

    /** <summary>
     *  Sms creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for testing SMS</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> testSms(@RequestBody SmsRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        SmsRequestDto response = service.testSms(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



}
