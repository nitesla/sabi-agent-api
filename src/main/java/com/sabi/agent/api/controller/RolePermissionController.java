package com.sabi.agent.api.controller;


import com.sabi.framework.dto.requestDto.RolePermissionDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.dto.responseDto.RolePermissionResponseDto;
import com.sabi.framework.models.RolePermission;
import com.sabi.framework.service.RolePermissionService;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(Constants.APP_CONTENT +"RolePermission")
public class RolePermissionController {
    private final RolePermissionService service;

    public RolePermissionController(RolePermissionService service) {
        this.service = service;
    }


    /** <summary>
     * RolePermission creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new RolePermission</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> assignPermission(@Validated @RequestBody RolePermissionDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.assignPermission(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * RolePermission update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating RolePermission</remarks>
     */

//    @PutMapping("")
//    public ResponseEntity<Response> updateRolePermission(@Validated @RequestBody  RolePermissionDto request,HttpServletRequest request1){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        RolePermissionResponseDto response = service.updateRolePermission(request,request1);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Update Successful");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }


    /** <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response> getRolePermission(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        RolePermissionResponseDto response = service.findRolePermission(id);
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
    public ResponseEntity<Response> getRolePermissions(@RequestParam(value = "roleId",required = false)Long roleId,
                                             @RequestParam(value = "isActive",required = false)Boolean isActive,
                                             @RequestParam(value = "page") int page,
                                             @RequestParam(value = "sortBy", required = false) String sort,
                                             @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        Page<RolePermission> response = service.findAll(roleId,isActive, PageRequest.of(page, pageSize, sortType));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response> removePermission(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.removePermission(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Permission removed successfully !");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @DeleteMapping("")
    public ResponseEntity<Response> removeMultiplePermission(@RequestParam("ids") List<Long> ids){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.deleteAllBYIds(ids);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Permission removed successfully !");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
