package com.sabi.agent.api.controller;


import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.requestDto.RoleDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.dto.responseDto.RoleResponseDto;
import com.sabi.framework.models.Role;
import com.sabi.framework.service.RoleService;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping(Constants.APP_CONTENT +"role")
public class RoleController {



    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }


    /** <summary>
     * Role creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Role</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createRole(@Validated @RequestBody RoleDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        RoleResponseDto response = service.createRole(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Role update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating role</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateRole(@Validated @RequestBody  RoleDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        RoleResponseDto response = service.updateRole(request);
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
    public ResponseEntity<Response> getRole(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        RoleResponseDto response = service.findRole(id);
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
    public ResponseEntity<Response> getRoles(@RequestParam(value = "name",required = false)String name,
                                             @RequestParam(value = "isActive",required = false)Boolean isActive,
                                                       @RequestParam(value = "page") int page,
                                                       @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<Role> response = service.findAll(name,isActive, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Role> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisEnable(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

}
