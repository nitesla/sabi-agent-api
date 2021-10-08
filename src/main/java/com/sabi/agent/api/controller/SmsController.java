//package com.sabi.agent.api.controller;
//
//
//import com.sabi.agent.core.dto.requestDto.SmsRequestDto;
//import com.sabi.agent.service.integrations.SmsService;
//import com.sabi.framework.utils.Constants;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//
//
//@SuppressWarnings("All")
//@RestController
//@Valid
//@Validated
//@RequestMapping(Constants.APP_CONTENT +"testsms")
//public class SmsController {
//
//    private final SmsService service;
//
//    public SmsController(SmsService service) {
//        this.service = service;
//    }
//
//    /** <summary>
//     *  Sms creation endpoint
//     * </summary>
//     * <remarks>this endpoint is responsible for testing SMS</remarks>
//     */
//
//    @PostMapping("")
//    public SmsRequestDto testSms(@Validated @RequestBody SmsRequestDto request) throws Exception{
//
//        SmsRequestDto response = service.testSms(request);
//        return response;
//    }
//
//
//
//}
