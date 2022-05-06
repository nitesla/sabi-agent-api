package com.sabi.agent.api.controller;

import com.sabi.agent.core.dto.requestDto.GlobalAdminAuthRequestDto;
import com.sabi.agent.core.dto.responseDto.GlobalAdminAuthResponse;
import com.sabi.agent.service.services.GlobalAdminAuthService;
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

@RestController
@RequestMapping(Constants.APP_CONTENT+"globalAuth")
public class GlobalAuthController {

    private final GlobalAdminAuthService service;

    public GlobalAuthController(GlobalAdminAuthService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Response> globalAdminAuthUser(@Validated @RequestBody GlobalAdminAuthRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        GlobalAdminAuthResponse response = service.authenticateUser(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }
}
