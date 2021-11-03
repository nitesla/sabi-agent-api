package com.sabi.agent.api.controller;

import com.sabi.agent.core.merchant_integration.request.MerchantSignUpRequest;
import com.sabi.agent.core.merchant_integration.response.MerchantDetailResponse;
import com.sabi.agent.core.merchant_integration.response.MerchantOtpResponse;
import com.sabi.agent.core.merchant_integration.response.MerchantOtpValidationResponse;
import com.sabi.agent.core.merchant_integration.response.MerchantSignUpResponse;
import com.sabi.agent.core.models.RegisteredMerchant;
import com.sabi.agent.service.services.MerchantService;
import com.sabi.framework.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(Constants.APP_CONTENT + "merchant")
public class MerchantController {

    @Autowired
    MerchantService service;

    @PostMapping("/signUp")
    public MerchantSignUpResponse merchantSignUp(@RequestBody MerchantSignUpRequest request,
                                                 @RequestHeader("fingerprint") String fingerPrint) {
        return service.createMerchant(request, fingerPrint);
    }

    @GetMapping("/otp")
    public MerchantOtpResponse generateOtp(@RequestHeader("fingerprint") String fingerPrint,
                                           @RequestParam("phoneNumber") String phoneNumber) throws UnsupportedEncodingException {
        return service.sendOtp(fingerPrint, phoneNumber);
    }

    @GetMapping("/validateOtp")
    public MerchantOtpValidationResponse validateOtp(@RequestHeader("fingerprint") String fingerPrint,
                                                     @RequestParam("userId") String userId,
                                                     @RequestParam("otp") String otp) {
        return service.validateOtp(fingerPrint, userId, otp);
    }

    @GetMapping("/find")
    public Page<RegisteredMerchant> findMerchants(@RequestHeader("fingerPrint") String fingerPrint,
                                                  @RequestParam(value = "agentId", required = false) String agentId,
                                                  @RequestParam(value = "merchantId", required = false) String merchantId,
                                                  @RequestParam("page") Integer page,
                                                  @RequestParam("pageSize") Integer pageSize) {
        return service.findMerchant(agentId, merchantId, PageRequest.of(page, pageSize));
    }

    @GetMapping("/{id}")
    public MerchantDetailResponse getSignedUpMerchantDetail(@RequestHeader("fingerPrint") String fingerPrint,
                                                       @PathVariable("id") String id){
        return service.merchantDetails(id, fingerPrint);
    }

}
