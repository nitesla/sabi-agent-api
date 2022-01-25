package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.agentDto.requestDto.AgentNetworkDto;
import com.sabi.agent.core.dto.requestDto.EnableDisEnableDto;
import com.sabi.agent.core.dto.responseDto.AgentNetworkResponseDto;
import com.sabi.agent.core.models.agentModel.AgentNetwork;
import com.sabi.agent.service.services.AgentNetworkService;
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

import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT + "agentnetwork")
public class AgentNetworkController {

    private final AgentNetworkService service;

    public AgentNetworkController(AgentNetworkService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Response> createAgentNetwork(@Validated @RequestBody AgentNetworkDto request) {
        HttpStatus httpCode;
        Response resp = new Response();
        AgentNetworkResponseDto response = service.createAgentNetwork(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateAgentNetwork(@Validated @RequestBody AgentNetworkDto request) {
        HttpStatus httpCode;
        Response resp = new Response();
        AgentNetworkResponseDto response = service.updateAgentNetwork(request);
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
    public ResponseEntity<Response> getAgentNetwork(@PathVariable Long id) {
        HttpStatus httpCode;
        Response resp = new Response();
        AgentNetworkResponseDto response = service.findAgentNetwork(id);
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
    public ResponseEntity<Response> getAgentNetworks(@RequestParam(value = "agentId", required = false) Long agentId,
                                                     @RequestParam(value = "page") int page,
                                                     @RequestParam(value = "isActive", required = false) Boolean isActive,
                                                     @RequestParam(value = "sortBy", required = false) String sort,
                                                     @RequestParam(value = "pageSize") int pageSize) {
        HttpStatus httpCode;
        Response resp = new Response();
        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        Page<AgentNetwork> response = service.findAll(agentId, isActive, PageRequest.of(page, pageSize, sortType));
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
     * <remarks>this endpoint is responsible for enabling and disenabling a AgentNetwork</remarks>
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

    @GetMapping("/status")
    public ResponseEntity<Response> getALlByStatus(@RequestParam("isActive") boolean isActive) {
        HttpStatus httpCode;
        Response resp = new Response();
        List<AgentNetworkResponseDto> response = service.getAllByStatus(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
