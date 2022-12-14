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
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("All")
@RestController
@Validated
@Valid
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
    public ResponseEntity<Response> getSupervisor(@Validated @PathVariable Long id){
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
     * Search all records endpoint
     * createdDate must be in this format yyyy-MM-dd
     * E.g: 2022-03-30
     * </summary>
     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
     */

    @GetMapping("")
    public ResponseEntity<Response> getSupervisors(@RequestParam(value = "supervisorName", required = false) String supervisorName,
                                                   @RequestParam(value = "createdDate",required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate createdDate,
                                                   @RequestParam(value = "fromDate",  required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                                                   @RequestParam(value = "toDate",  required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
                                                   @RequestParam(value = "isActive", required = false) Boolean isActive,
                                                   @RequestParam(value = "page") int page,
                                                   @RequestParam(value = "sortBy", required = false) String sort,
                                                   @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        LocalDateTime fromLocalDate = fromDate != null ? fromDate.atStartOfDay() : null;
        LocalDateTime toLocalDate = toDate !=null ? toDate.atStartOfDay() : null;
        Page<Supervisor> response = service.findAll(supervisorName,createdDate,fromLocalDate, toLocalDate, isActive,PageRequest.of(page, pageSize, sortType));
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

    @GetMapping("/list")
    public ResponseEntity<Response> getAllByStatus(@Validated @RequestParam(value = "isActive") Boolean isActive){
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
