package com.sabi.agent.api.controller;

import com.sabi.agent.core.dto.agentDto.requestDto.Verification;
import com.sabi.agent.core.models.agentModel.AgentVerification;
import com.sabi.agent.service.services.AgentVerificationService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.APP_CONTENT +"agentverification")
public class AgentVerificationController {



    private final AgentVerificationService service;

    public AgentVerificationController(AgentVerificationService service) {
        this.service = service;
    }




    @GetMapping("/{id}")
    public ResponseEntity<Response> getAgentVerification(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        AgentVerification response = service.findAgentVerification(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }




    @GetMapping("")
    public ResponseEntity<Response> getAgentsVerification(@RequestParam(value = "name",required = false) String name,
                                                          @RequestParam(value = "agentId",required = false)Long agentId,
                                                          @RequestParam(value = "page") int page,
                                                          @RequestParam(value = "sortBy", required = false) String sort,
                                                          @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Sort sortType = sort.equalsIgnoreCase("asc") ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        Page<AgentVerification> response = service.findAll(name,agentId, PageRequest.of(page, pageSize, sortType));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("/verification")
    public ResponseEntity<Response> verification(@Validated @RequestBody Verification request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.verification(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }




}
