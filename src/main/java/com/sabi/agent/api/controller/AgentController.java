package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.agentDto.requestDto.AgentBvnVerificationDto;
import com.sabi.agent.core.dto.agentDto.requestDto.AgentUpdateDto;
import com.sabi.agent.core.dto.agentDto.requestDto.AgentVerificationDto;
import com.sabi.agent.core.dto.agentDto.requestDto.CreateAgentRequestDto;
import com.sabi.agent.core.dto.requestDto.EmailVerificationDto;
import com.sabi.agent.core.dto.requestDto.EnableDisEnableDto;
import com.sabi.agent.core.dto.requestDto.ValidateOTPRequest;
import com.sabi.agent.core.dto.responseDto.AgentActivationResponse;
import com.sabi.agent.core.dto.responseDto.AgentUpdateResponseDto;
import com.sabi.agent.core.dto.responseDto.CreateAgentResponseDto;
import com.sabi.agent.core.dto.responseDto.QueryAgentResponseDto;
import com.sabi.agent.core.models.agentModel.Agent;
import com.sabi.agent.service.services.AgentService;
import com.sabi.framework.dto.requestDto.ChangePasswordDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT +"agent")
public class AgentController {

    private  static final Logger logger = LoggerFactory.getLogger(AgentController.class);

    private final AgentService service;

    public AgentController(AgentService service) {
        this.service = service;
    }


    /** <summary>
     * Agent creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Agent</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> agentSignUp(@Validated @RequestBody CreateAgentRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        CreateAgentResponseDto response = service.agentSignUp(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



//    @PutMapping("/resendotp")
//    public ResponseEntity<Response> reSendOtp(@Validated @RequestBody ResendOTP request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        service.resendAgentOTP(request);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("OTP sent successfully");
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }


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
    public ResponseEntity<Response> updateAgent(@Validated @RequestBody AgentUpdateDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        AgentUpdateResponseDto response = service.updateAgent(request);
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
        QueryAgentResponseDto response = service.findAgent(id);
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
                                               @RequestParam(value = "referralCode",required = false)String referralCode,
                                             @RequestParam(value = "page") int page,
                                             @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<Agent> response = service.findAll(userId,isActive,referralCode, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a Target Type</remarks>
     */


    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisEnableAgent(request);
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


    @PutMapping("/agentbvnverifications")
    public ResponseEntity<Response> agentBvnVerifications(@Validated @RequestBody AgentBvnVerificationDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.agentBvnVerifications(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


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
        service.agentEmailVerifications(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Email verified Successfully");
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

}
