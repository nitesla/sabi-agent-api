package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.requestDto.EnableDisEnableDto;
import com.sabi.agent.core.dto.requestDto.TargetTypeDto;
import com.sabi.agent.core.dto.responseDto.TargetTypeResponseDto;
import com.sabi.agent.core.models.TargetType;
import com.sabi.agent.service.services.TargetTypeService;
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

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("All")
@RestController
@Valid
@Validated
@RequestMapping(Constants.APP_CONTENT +"targetType")
public class TargetTypeController {

    private final TargetTypeService service;

    public TargetTypeController(TargetTypeService service) {
        this.service = service;
    }

    /** <summary>
     * Target Type creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Target Type</remarks>
     */


    @PostMapping("")
    public ResponseEntity<Response> createTargetType(@Validated @RequestBody TargetTypeDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        TargetTypeResponseDto response = service.createTargetType(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Target Type update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating Target Type</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateTargetType(@Validated @RequestBody  TargetTypeDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        TargetTypeResponseDto response = service.updateTargetType(request);
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
    public ResponseEntity<Response> getTargetType(@Validated @PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        TargetTypeResponseDto response = service.findTargetType(id);
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
    public ResponseEntity<Response> getTargetTypes(@RequestParam(value = "name",required = false)String name,
                                              @RequestParam(value = "isActive",required = false)Boolean isActive,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "sortBy", required = false) String sort,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        Page<TargetType> response = service.findAll(name, isActive, PageRequest.of(page, pageSize, sortType));
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


    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisableTargetType(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllByStatus(@Validated @RequestParam(value = "isActive") Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<TargetType> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
