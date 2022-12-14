package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.ValidateEmailOtpRequest;
import com.sabi.agent.core.dto.agentDto.requestDto.AgentPhotoRequest;
import com.sabi.agent.core.dto.agentDto.requestDto.AgentUpdateDto;
import com.sabi.agent.core.dto.agentDto.requestDto.AgentVerificationDto;
import com.sabi.agent.core.dto.agentDto.requestDto.CreateAgentRequestDto;
import com.sabi.agent.core.dto.requestDto.EmailVerificationDto;
import com.sabi.agent.core.dto.requestDto.EnableDisEnableDto;
import com.sabi.agent.core.dto.requestDto.ResendOTP;
import com.sabi.agent.core.dto.requestDto.ValidateOTPRequest;
import com.sabi.agent.core.dto.responseDto.AgentActivationResponse;
import com.sabi.agent.core.dto.responseDto.AgentUpdateResponseDto;
import com.sabi.agent.core.dto.responseDto.CreateAgentResponseDto;
import com.sabi.agent.core.dto.responseDto.EmailVerificationResponseDto;
import com.sabi.agent.core.models.agentModel.Agent;
import com.sabi.agent.service.services.AgentService;
import com.sabi.framework.dto.requestDto.ChangePasswordDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.exceptions.NotFoundException;
import com.sabi.framework.models.User;
import com.sabi.framework.service.UserService;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(Constants.APP_CONTENT +"agent")
@Validated
public class AgentController {

    private  static final Logger logger = LoggerFactory.getLogger(AgentController.class);

    private final AgentService service;
    private final UserService userService;

    public AgentController(AgentService service,UserService userService) {
        this.service = service;
        this.userService = userService;
    }


    /** <summary>
     * Agent creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Agent</remarks>
     */

    @PostMapping("/signup")
    public ResponseEntity<Response> agentSignUp(@Validated @RequestBody CreateAgentRequestDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        CreateAgentResponseDto response = service.agentSignUp(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    @PutMapping("/resendotp")
    public ResponseEntity<Response> reSendOtp(@Validated @RequestBody ResendOTP request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.resendAgentOTP(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("OTP sent successfully");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("/validateotp")
    public ResponseEntity<Response> validateOTP(@Validated @RequestBody ValidateOTPRequest request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.validateOTP(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }




    @PutMapping("/passwordactivation")
    public ResponseEntity<Response> agentPasswordActivation(@Validated @RequestBody ChangePasswordDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        AgentActivationResponse response = service.agentPasswordActivation(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Password changed successfully");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Agent update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating agent</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateAgent(@Validated @RequestBody AgentUpdateDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        AgentUpdateResponseDto response = service.updateAgent(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }






    /** <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */

    @GetMapping("/{id}")
    public ResponseEntity<Response> getAgent(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        Agent response = service.findAgent(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
    /** <summary>
     * Get all records endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
     */

    @GetMapping("")
    public ResponseEntity<Response> getAgents(@RequestParam(value = "userId",required = false)Long userId,
                                               @RequestParam(value = "isActive",required = false)Boolean isActive,
                                               @RequestParam(value = "firstName",required = false)String firstName,
                                               @RequestParam(value = "lastName",required = false)String lastName,
                                               @RequestParam(value = "referralCode", required = false) String referralCode,
                                              @RequestParam(value = "referrer",required = false)String referrer,
                                               @RequestParam(value = "page") int page,
                                              @RequestParam(value = "sortBy", required = false) String sort,
                                               @RequestParam(value = "pageSize") int pageSize) throws Exception {

        HttpStatus httpCode ;
        Response resp = new Response();
        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        if(firstName !=null ){
                Page<User> response = service.findAgentUser(firstName,lastName, PageRequest.of(page, pageSize, sortType));
                resp.setCode(CustomResponseCode.SUCCESS);
                resp.setDescription("Record fetched successfully !");
                resp.setData(response);
                httpCode = HttpStatus.OK;
                return new ResponseEntity<>(resp, httpCode);
        }else {
            Page<Agent> response = service.findAllAgents(userId, isActive,referralCode ,referrer,PageRequest.of(page, pageSize, sortType));
            resp.setCode(CustomResponseCode.SUCCESS);
            resp.setDescription("Record fetched successfully !");
            resp.setData(response);
            httpCode = HttpStatus.OK;
            return new ResponseEntity<>(resp, httpCode);
        }
    }




    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a Target Type</remarks>
     */


    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisEnableDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisEnableAgent(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }




    @PutMapping("/agentaddressverifications")
    public ResponseEntity<Response> agentAddressVerifications(@Validated @RequestBody AgentVerificationDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.agentAddressVerifications(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


//    @PutMapping("/agentbvnverifications")
//    public ResponseEntity<Response> agentBvnVerifications(@Validated @RequestBody AgentBvnVerificationDto request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        service.agentBvnVerifications(request);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Successful");
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }


    @PutMapping("/agentidcardverifications")
    public ResponseEntity<Response> agentIdCardVerifications(@Validated @RequestBody AgentVerificationDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.agentIdCardVerifications(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @PutMapping("/emailverifications")
    public ResponseEntity<Response> emailVerifications(@Validated @RequestBody EmailVerificationDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        EmailVerificationResponseDto response =service.agentEmailVerifications(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Email verified Successfully");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("/phoneverifications")
    public ResponseEntity<Response> phoneVerifications(@Validated @RequestBody EmailVerificationDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        EmailVerificationResponseDto response =service.agentPhoneVerifications(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Phone verified Successfully");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @PutMapping("/emailotpvalidation")
    public ResponseEntity<Response> emailOtpValidation(@Validated @RequestBody ValidateEmailOtpRequest request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.validateOTPForEmailVerification(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Agent> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PutMapping("/profilephoto")
    public ResponseEntity<Response> addPhoto(@RequestBody @Valid AgentPhotoRequest request){
        HttpStatus httpCode ;
        Response resp = new Response();
        Agent response = service.addAgentPhoto(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Profile Photo added successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/searchName")
    public ResponseEntity<Response> searchByName(@RequestParam("name") String name,
                                                 @RequestParam("referralCode") String referralCode,
                                                 @RequestParam(value = "page") int page,
                                                 @RequestParam(value = "sortBy", required = false) String sort,
                                                 @RequestParam(value = "pageSize") int pageSize){
        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        if(name == null || name.isEmpty()) throw new NotFoundException(CustomResponseCode.NOT_ACCEPTABLE, "A search term is required needed" );
        logger.info("name from search term is {}", name);
        HttpStatus httpCode ;
        Response resp = new Response();
        List<User> response = service.findAgentUserWithReferralCode(name, referralCode, PageRequest.of(page, pageSize, sortType));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/filter")
    public ResponseEntity<Response> filterAdmin(@RequestParam(value = "agentName", required = false) String agentName,
                                                @RequestParam(value = "agentCategory", required = false) String agentCategory,
                                                @RequestParam(value = "verificationStatus", required = false) String verificationStatus,
                                                @RequestParam(value = "status", required = false) Integer status,
                                                @RequestParam(value = "isActive", required = false) Boolean isActive,
                                                @RequestParam(value = "startDate", required = false) String startDate,
                                                @RequestParam(value = "endDate", required = false) String endDate,
                                                @RequestParam(value = "page") int page,
                                                @RequestParam(value = "sortBy", required = false) String sort,
                                                @RequestParam(value = "pageSize") int pageSize) {
        Response resp = new Response();
        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        Page<Map> response = service.filterAgent(agentName, agentCategory, verificationStatus, status, isActive, startDate, endDate, PageRequest.of(page, pageSize, sortType));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
