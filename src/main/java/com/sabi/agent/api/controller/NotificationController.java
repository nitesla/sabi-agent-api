package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.requestDto.NotificationRequestDto;
import com.sabi.agent.service.integrations.NotificationService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@SuppressWarnings("All")
@RestController
@Validated
@Valid
@RequestMapping(Constants.APP_CONTENT +"notification")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    /** <summary>
     *  Email Notification endpoint
     * </summary>
     * <remarks>this endpoint is responsible for email notification</remarks>
     */

    @PostMapping("/email")
    public ResponseEntity<Response> email(@Validated @RequestBody NotificationRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        NotificationRequestDto response = service.emailNotificationRequest(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     *  Sms Notification creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for sms notification</remarks>
     */

    @PostMapping("/sms")
    public ResponseEntity<Response> sms(@Validated @RequestBody NotificationRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        NotificationRequestDto response = service.smsNotificationRequest(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



}
