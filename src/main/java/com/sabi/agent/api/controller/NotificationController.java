package com.sabi.agent.api.controller;

import com.sabi.framework.notification.requestDto.NotificationRequestDto;
import com.sabi.framework.notification.service.NotificationService;
import com.sabi.framework.utils.Constants;
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
    public NotificationRequestDto email(@Validated @RequestBody NotificationRequestDto request) throws Exception{

        NotificationRequestDto response = service.emailNotificationRequest(request);

        return response;
    }

    /** <summary>
     *  Sms Notification creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for sms notification</remarks>
     */

    @PostMapping("/sms")
    public NotificationRequestDto sms(@Validated @RequestBody NotificationRequestDto request) throws Exception{

        NotificationRequestDto response = service.smsNotificationRequest(request);
        return response;
    }



}
