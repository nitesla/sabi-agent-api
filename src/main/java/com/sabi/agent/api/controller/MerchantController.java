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
import org.springframework.data.domain.Sort;
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
                                                  @RequestParam(value = "firstName", required = false) String firstName,
                                                  @RequestParam(value = "lastName", required = false) String lastName,
                                                  @RequestParam("page") Integer page,
                                                  @RequestParam(value = "sortBy", required = false) String sort,
                                                  @RequestParam("pageSize") Integer pageSize) {
        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        return service.findMerchant(agentId, merchantId, firstName, lastName,PageRequest.of(page, pageSize, sortType));
    }

    @GetMapping("/{id}")
    public MerchantDetailResponse getSignedUpMerchantDetail(@RequestHeader("fingerPrint") String fingerPrint,
                                                       @PathVariable("id") String id){
        return service.merchantDetails(id, fingerPrint);
    }

}
