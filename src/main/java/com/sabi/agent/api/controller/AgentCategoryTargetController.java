package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.agentDto.requestDto.AgentCategoryTargetDto;
import com.sabi.agent.core.dto.requestDto.EnableDisEnableDto;
import com.sabi.agent.core.dto.responseDto.AgentCategoryTargetResponseDto;
import com.sabi.agent.core.models.agentModel.AgentCategoryTarget;
import com.sabi.agent.service.services.AgentCategoryTargetService;
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
@RequestMapping(Constants.APP_CONTENT +"agentcategorytarget")
public class AgentCategoryTargetController {

    private final AgentCategoryTargetService service;

    public AgentCategoryTargetController(AgentCategoryTargetService service) {
        this.service = service;
    }

    /** <summary>
     *  Agent Category Target creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Agent Category Target</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createAgentCategoryTarget(@Validated @RequestBody AgentCategoryTargetDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        AgentCategoryTargetResponseDto response = service.createAgentCategoryTarget(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Agent Category Target update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating Agent Category Target</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateAgentCategoryTarget(@Validated @RequestBody  AgentCategoryTargetDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        AgentCategoryTargetResponseDto response = service.updateAgentCategoryTarget(request);
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
    public ResponseEntity<Response> getAgentCategoryTarget(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        AgentCategoryTargetResponseDto response = service.findAgentCategoryTarget(id);
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
    public ResponseEntity<Response> getAgentCategoryTargets(@RequestParam(value = "name",required = false)String name,
                                                            @RequestParam(value = "isActive",required = false)Boolean isActive,
                                                            @RequestParam(value = "min",required = false) int min,
                                                            @RequestParam(value = "max",required = false) int max,
                                                            @RequestParam(value = "supermax",required = false) int supermax,
                                                            @RequestParam(value = "page") int page,
                                                            @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<AgentCategoryTarget> response = service.findAll(name, isActive, min, max, supermax, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a Agent Category Target</remarks>
     */

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisableAgtCatTarget(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllByStatus(@Param(value = "isActive") Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<AgentCategoryTarget> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
