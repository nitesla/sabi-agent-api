//package com.sabi.agent.api.controller;
//
//
//import com.sabi.framework.notification.requestDto.NotificationRequestDto;
//import com.sabi.framework.notification.requestDto.SmsRequest;
//import com.sabi.framework.notification.responseDto.NotificationResponseDto;
//import com.sabi.framework.service.NotificationService;
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
//@Validated
//@Valid
//@RequestMapping(Constants.APP_CONTENT +"notification")
//public class NotificationController {
//
//    private final NotificationService service;
//
//    public NotificationController(NotificationService service) {
//        this.service = service;
//    }
//
//
//
//
//    @PostMapping("/email")
//    public NotificationResponseDto emailNotification (@RequestBody NotificationRequestDto request) throws Exception {
//        System.out.println("::::; EMAIL ::::::"+ request);
//        NotificationResponseDto response= service.emailNotificationRequest(request);
//        return response;
//    }
//
//
//
//
//    @PostMapping("/sms")
//    public String smsNotification (@RequestBody SmsRequest request) throws Exception {
//        String response= service.smsNotificationRequest(request);
//        return response;
//    }
//
//
//
//
//
//}
