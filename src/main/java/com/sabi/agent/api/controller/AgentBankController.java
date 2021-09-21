package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.agentDto.requestDto.AgentBankDto;
import com.sabi.agent.core.dto.requestDto.EnableDisEnableDto;
import com.sabi.agent.core.dto.responseDto.AgentBankResponseDto;
import com.sabi.agent.core.models.agentModel.AgentBank;
import com.sabi.agent.service.services.AgentBankService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT +"agentBank")
public class AgentBankController {

    private final AgentBankService service;

    public AgentBankController(AgentBankService service) {
        this.service = service;
    }

    /** <summary>
     *  Agent Bank creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Agent Bank</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createAgentBank(@Validated @RequestBody AgentBankDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        AgentBankResponseDto response = service.createAgentBank(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Agent Bank update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating Agent bank</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateAgentBank(@Validated @RequestBody  AgentBankDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        AgentBankResponseDto response = service.updateAgentBank(request);
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
    public ResponseEntity<Response> getAgentBank(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        AgentBankResponseDto response = service.findAgentBank(id);
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
    public ResponseEntity<Response> getAgentCategoryTargets(@RequestParam(value = "agentId",required = false)Long agentId,
                                                            @RequestParam(value = "bankId",required = false)Long bankId,
                                                            @RequestParam(value = "accountNumber",required = false) int accountNumber,
                                                            @RequestParam(value = "page") int page,
                                                            @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<AgentBank> response = service.findAll(agentId, bankId, accountNumber, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a Agent Bank</remarks>
     */

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisableAgentBank(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllByStatus(@Param(value = "isActive") Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<AgentBank> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
