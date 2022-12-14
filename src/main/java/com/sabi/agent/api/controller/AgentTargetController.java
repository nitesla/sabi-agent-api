package com.sabi.agent.api.controller;

import com.sabi.agent.core.dto.agentDto.requestDto.AgentTargetDto;
import com.sabi.agent.core.dto.requestDto.EnableDisEnableDto;
import com.sabi.agent.core.dto.responseDto.AgentTargetResponseDto;
import com.sabi.agent.core.models.agentModel.AgentTarget;
import com.sabi.agent.service.services.AgentTargetService;
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

@SuppressWarnings("ALL")
@RestController
@RequestMapping(Constants.APP_CONTENT +"agenttarget")
public class AgentTargetController {
    private final AgentTargetService service;

    public AgentTargetController(AgentTargetService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Response> createAgentTarget(@Validated @RequestBody AgentTargetDto request) {
        HttpStatus httpCode;
        Response resp = new Response();
        AgentTargetResponseDto response = service.createAgentTarget(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateAgentTarget(@Validated @RequestBody AgentTargetDto request) {
        HttpStatus httpCode;
        Response resp = new Response();
        AgentTargetResponseDto response = service.updateAgentTarget(request);
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
    public ResponseEntity<Response> getAgentTarget(@PathVariable Long id) {
        HttpStatus httpCode;
        Response resp = new Response();
        AgentTargetResponseDto response = service.findAgentTarget(id);
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
    public ResponseEntity<Response> getAgentTargets(@RequestParam(value = "name", required = false) String name,
                                                    @RequestParam(value = "min", required = false) Integer min,
                                                    @RequestParam(value = "max", required = false) Integer max,
                                                    @RequestParam(value = "agentId", required = false) Integer agentId,
                                                    @RequestParam(value = "superMax", required = false) Integer superMax,
                                                    @RequestParam(value = "page") int page,
                                                     @RequestParam(value = "isActive", required = false) Boolean isActive,
                                                    @RequestParam(value = "sortBy", required = false) String sort,
                                                     @RequestParam(value = "pageSize") int pageSize) {
        HttpStatus httpCode;
        Response resp = new Response();
        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        Page<AgentTarget> response = service.findAll(name,isActive,min,max,superMax,agentId,PageRequest.of(page, pageSize, sortType));
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
     * <remarks>this endpoint is responsible for enabling and disenabling a AgentTarget</remarks>
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



    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<AgentTarget> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
