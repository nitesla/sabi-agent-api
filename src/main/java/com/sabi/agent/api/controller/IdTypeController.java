package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.requestDto.EnableDisEnableDto;
import com.sabi.agent.core.dto.requestDto.IdTypeDto;
import com.sabi.agent.core.dto.responseDto.IdTypeResponseDto;
import com.sabi.agent.core.models.IdType;
import com.sabi.agent.service.services.IdTypeService;
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

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT +"idtype")
public class IdTypeController {


    private final IdTypeService service;

    public IdTypeController(IdTypeService service) {
        this.service = service;
    }



    /** <summary>
     * IdType creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new idType</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createIdType(@Validated @RequestBody IdTypeDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        IdTypeResponseDto response = service.createIdType(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }





    /** <summary>
     * IdType update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating idType</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateIdType(@Validated @RequestBody  IdTypeDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        IdTypeResponseDto response = service.updateIdType(request);
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
    public ResponseEntity<Response> getIdType(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        IdTypeResponseDto response = service.findIdType(id);
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
    public ResponseEntity<Response> getIdTypes(@RequestParam(value = "name",required = false)String name,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "sortBy", required = false) String sort,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Sort sortType = sort.equalsIgnoreCase("asc") ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        Page<IdType> response = service.findAll(name, PageRequest.of(page, pageSize, sortType));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a State</remarks>
     */

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
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
        List<IdType> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
