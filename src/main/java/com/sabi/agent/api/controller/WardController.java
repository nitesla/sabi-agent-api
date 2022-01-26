package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.requestDto.EnableDisEnableDto;
import com.sabi.agent.core.dto.requestDto.WardDto;
import com.sabi.agent.core.dto.responseDto.WardResponseDto;
import com.sabi.agent.core.models.Ward;
import com.sabi.agent.service.services.WardService;
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
@Valid
@RestController
@RequestMapping(Constants.APP_CONTENT +"ward")
public class WardController {

    private final WardService service;

    public WardController(WardService service) {
        this.service = service;
    }

    /** <summary>
     *  Ward creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Ward</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createWard(@Validated @RequestBody WardDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        WardResponseDto response = service.createWard(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Ward update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating Ward</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateWard(@Validated @RequestBody  WardDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        WardResponseDto response = service.updateWard(request);
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
    public ResponseEntity<Response> getWard(@Validated @PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        WardResponseDto response = service.findWard(id);
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
    public ResponseEntity<Response> getWards(@RequestParam(value = "name",required = false)String name,
                                             @RequestParam(value = "isActive",required = false)Boolean isActive,
                                             @RequestParam(value = "lgaId",required = false)Long lgaId,
                                              @RequestParam(value = "page") int page,
                                             @RequestParam(value = "sortBy", required = false) String sort,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        Page<Ward> response = service.findAll(name, isActive, lgaId, PageRequest.of(page, pageSize, sortType));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a Ward</remarks>
     */

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisableWard(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllByStatus(@Validated @RequestParam(value = "isActive") Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Ward> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

}
