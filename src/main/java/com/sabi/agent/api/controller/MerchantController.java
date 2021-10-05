package com.sabi.agent.api.controller;

import com.sabi.agent.core.merchant_integration.request.MerchantSignUpRequest;
import com.sabi.agent.core.merchant_integration.response.MerchantSignUpResponse;
import com.sabi.agent.service.services.MerchantService;
import com.sabi.framework.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.APP_CONTENT + "merchant")
public class MerchantController {

    @Autowired
    MerchantService service;

    @PostMapping
    public MerchantSignUpResponse merchantSignUp(@RequestBody MerchantSignUpRequest request,
                                                 @RequestHeader("fingerprint") String fingerPrint){
        return service.createMerchant(request, fingerPrint);
    }
}
