package com.sabi.agent.api.controller;

import com.sabi.agent.core.dto.agentDto.requestDto.AgentSupervisorDto;
import com.sabi.agent.core.dto.requestDto.EnableDisEnableDto;
import com.sabi.agent.core.dto.responseDto.AgentSupervisorResponseDto;
import com.sabi.agent.core.models.agentModel.AgentSupervisor;
import com.sabi.agent.service.services.AgentSupervisorService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT + "agentsupervisor")
public class AgentSupervisorController {
    private final AgentSupervisorService service;

    public AgentSupervisorController(AgentSupervisorService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Response> createAgentSupervisor(@Validated @RequestBody AgentSupervisorDto request) {
        HttpStatus httpCode;
        Response resp = new Response();
        AgentSupervisorResponseDto response = service.createAgentSupervisor(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateAgentSupervisor(@Validated @RequestBody AgentSupervisorDto request) {
        HttpStatus httpCode;
        Response resp = new Response();
        AgentSupervisorResponseDto response = service.updateAgentSupervisor(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    /**
     * <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response> getAgentSupervisor(@PathVariable Long id) {
        HttpStatus httpCode;
        Response resp = new Response();
        AgentSupervisorResponseDto response = service.findAgentSupervisor(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    /**
     * <summary>
     * Get all records endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
     */
    @GetMapping("")
    public ResponseEntity<Response> getAgentSupervisors(
                                                     @RequestParam(value = "page") int page,
                                                     @RequestParam(value = "pageSize") int pageSize) {
        HttpStatus httpCode;
        Response resp = new Response();
        List<AgentSupervisorResponseDto> response = service.findAll(PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    /**
     * <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a AgentSupervisor</remarks>
     */

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisEnableDto request) {
        HttpStatus httpCode;
        Response resp = new Response();
        service.enableDisEnableState(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
