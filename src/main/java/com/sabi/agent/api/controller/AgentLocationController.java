package com.sabi.agent.api.controller;

import com.sabi.agent.core.dto.agentDto.requestDto.AgentLocationDto;
import com.sabi.agent.core.dto.agentDto.requestDto.AgentNetworkDto;
import com.sabi.agent.core.dto.requestDto.EnableDisEnableDto;
import com.sabi.agent.core.dto.responseDto.AgentLocationResponseDto;
import com.sabi.agent.core.dto.responseDto.AgentNetworkResponseDto;
import com.sabi.agent.core.dto.responseDto.MarketResponseDto;
import com.sabi.agent.core.models.agentModel.AgentLocation;
import com.sabi.agent.core.models.agentModel.AgentNetwork;
import com.sabi.agent.service.services.AgentLocationService;
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
@RequestMapping(Constants.APP_CONTENT +"agentlocation")
public class AgentLocationController {
    private final AgentLocationService service;

    public AgentLocationController(AgentLocationService agentLocationService) {
        this.service = agentLocationService;
    }

    @PostMapping("")
    public ResponseEntity<Response> createAgentLocation(@Validated @RequestBody AgentLocationDto request) {
        HttpStatus httpCode;
        Response resp = new Response();
        AgentLocationResponseDto response = service.createAgentLocation(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateAgentLocation(@Validated @RequestBody AgentLocationDto request) {
        HttpStatus httpCode;
        Response resp = new Response();
        AgentLocationDto response = service.updateAgentLocation(request);
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
    public ResponseEntity<Response> getAgentLocation(@PathVariable Long id) {
        HttpStatus httpCode;
        Response resp = new Response();
        AgentLocationResponseDto response = service.findAgentLocation(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("")
    public ResponseEntity<Response> getAgentLocations(@RequestParam(value = "name", required = false) String name,
                                                     @RequestParam(value = "page") int page,
                                                     @RequestParam(value = "isActive", required = false) Boolean isActive,
                                                     @RequestParam(value = "pageSize") int pageSize) {
        HttpStatus httpCode;
        Response resp = new Response();
        Page<AgentLocation> response = service.findAll(name, isActive, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

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
    public ResponseEntity<Response> getALlByStatus(@RequestParam("isActive") boolean isActive){
        HttpStatus httpCode;
        Response resp = new Response();
        List<AgentLocationResponseDto> response = service.getAllByStatus(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
