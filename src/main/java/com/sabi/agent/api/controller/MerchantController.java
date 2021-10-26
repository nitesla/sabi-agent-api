package com.sabi.agent.api.controller;

import com.sabi.agent.core.merchant_integration.request.MerchantSignUpRequest;
import com.sabi.agent.core.merchant_integration.response.MerchantOtpResponse;
import com.sabi.agent.core.merchant_integration.response.MerchantOtpValidationResponse;
import com.sabi.agent.core.merchant_integration.response.MerchantSignUpResponse;
import com.sabi.agent.service.services.MerchantService;
import com.sabi.framework.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(Constants.APP_CONTENT + "merchant")
public class MerchantController {

    @Autowired
    MerchantService service;

    @PostMapping("/signUp")
    public MerchantSignUpResponse merchantSignUp(@RequestBody MerchantSignUpRequest request,
                                                 @RequestHeader("fingerprint") String fingerPrint){
        return service.createMerchant(request, fingerPrint);
    }

    @GetMapping("/otp")
    public MerchantOtpResponse generateOtp(@RequestHeader("fingerprint") String fingerPrint,
                                           @PathVariable("phoneNumber") String msisdn) throws UnsupportedEncodingException {
        return service.sendOtp(fingerPrint, msisdn);
    }

    @GetMapping("/validateOtp")
    public MerchantOtpValidationResponse validateOtp(@RequestHeader("fingerprint") String fingerPrint,
                                                     @PathVariable("userId") String userId,
                                                     @PathVariable("otp") String otp){
        return service.validateOtp(fingerPrint, userId, otp);
    }

}
