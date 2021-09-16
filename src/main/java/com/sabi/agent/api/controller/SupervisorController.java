package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.requestDto.EnableDisEnableDto;
import com.sabi.agent.core.dto.requestDto.SupervisorDto;
import com.sabi.agent.core.dto.responseDto.SupervisorResponseDto;
import com.sabi.agent.core.models.Supervisor;
import com.sabi.agent.service.services.SupervisorService;
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
@RequestMapping(Constants.APP_CONTENT +"supervisor")
public class SupervisorController {

    private final SupervisorService service;

    public SupervisorController(SupervisorService service) {
        this.service = service;
    }

    /** <summary>
     * Supervisor creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Supervisor</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createSupervisor(@Validated @RequestBody SupervisorDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        SupervisorResponseDto response = service.createSupervisor(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Supervisor update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating Supervisor</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateSupervisor(@Validated @RequestBody  SupervisorDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        SupervisorResponseDto response = service.updateSupervisor(request);
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
    public ResponseEntity<Response> getSupervisor(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        SupervisorResponseDto response = service.findSupervisor(id);
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
    public ResponseEntity<Response> getSupervisors(@RequestParam(value = "page") int page, @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<Supervisor> response = service.findAll(PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a Supervisor</remarks>
     */

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisableSupervisor(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("")
    public ResponseEntity<Response> getAllByStatus(@Param(value = "isActive") Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Supervisor> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

}
