package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.requestDto.NotificationRequestDto;
import com.sabi.agent.core.models.notifications.Notification;
import com.sabi.agent.service.services.NotificationService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@SuppressWarnings("All")
@RestController
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
    public ResponseEntity<Response> email(@RequestBody NotificationRequestDto request,
                                            @RequestParam(value = "fingerprint",required = true)String fingerprint){
        HttpStatus httpCode ;
        Response resp = new Response();
        NotificationRequestDto response = service.emailNotificationRequest(request, fingerprint);
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
    public ResponseEntity<Response> sms(@RequestParam(value = "message",required = true)String message,
                                          @RequestParam(value = "email",required = true)String email,
                                          @RequestParam(value = "title",required = true)String title,
                                          @RequestParam(value = "fingerprint",required = true)String fingerprint){
        HttpStatus httpCode ;
        Response resp = new Response();
        Notification response = service.smsNotificationRequest(message, email, title, fingerprint);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



}
