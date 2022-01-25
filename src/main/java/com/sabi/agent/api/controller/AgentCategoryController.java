package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.agentDto.requestDto.AgentCategoryDto;
import com.sabi.agent.core.dto.agentDto.requestDto.AgentPhotoRequest;
import com.sabi.agent.core.dto.requestDto.EnableDisEnableDto;
import com.sabi.agent.core.dto.responseDto.AgentCategoryResponseDto;
import com.sabi.agent.core.models.agentModel.AgentCategory;
import com.sabi.agent.service.services.AgentCategoryService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.var;
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
@RequestMapping(Constants.APP_CONTENT + "agentcategory")
public class AgentCategoryController {


    private final AgentCategoryService service;

    public AgentCategoryController(AgentCategoryService service) {
        this.service = service;
    }


    /**
     * <summary>
     * AgentCategory creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new AgentCategory</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createAgentCategory(@Validated @RequestBody AgentCategoryDto request) {
        HttpStatus httpCode;
        Response resp = new Response();
        AgentCategoryResponseDto response = service.createAgentCategory(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    /**
     * <summary>
     * AgentCategory update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating AgentCategory</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateAgentCategory(@Validated @RequestBody AgentCategoryDto request) {
        HttpStatus httpCode;
        Response resp = new Response();
        AgentCategoryResponseDto response = service.updateAgentCategory(request);
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
    public ResponseEntity<Response> getAgentCategory(@PathVariable Long id) {
        HttpStatus httpCode;
        Response resp = new Response();
        AgentCategoryResponseDto response = service.findAgentCategory(id);
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
    public ResponseEntity<Response> getAgentCategories(@RequestParam(value = "name", required = false) String name,
                                                       @RequestParam(value = "page") int page,
                                                       @RequestParam(value = "isActive", required = false) Boolean isActive,
                                                       @RequestParam(value = "sortBy", required = false) String sort,
                                                       @RequestParam(value = "pageSize") int pageSize) {
        HttpStatus httpCode;
        Response resp = new Response();
        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        Page<AgentCategory> response = service.findAll(name, isActive,PageRequest.of(page, pageSize, sortType));
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
     * <remarks>this endpoint is responsible for enabling and disenabling a bank</remarks>
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

    @GetMapping("/status")
    public ResponseEntity<Response> getALlByStatus(@RequestParam("isActive") boolean isActive){
        HttpStatus httpCode;
        Response resp = new Response();
        List<AgentCategoryResponseDto> response = service.getAllByStatus(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/default")
    public ResponseEntity<Response> getDefault(@RequestParam("isActive") boolean isActive){
        HttpStatus httpCode;
        Response resp = new Response();
        List<AgentCategoryResponseDto> response = service.getDefault();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/isDefault/{id}")
    public ResponseEntity<Response> setDefault(@PathVariable("id") long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        var res = service.setDefalult(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(res);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    public ResponseEntity<Response> setImage(@RequestBody @Valid AgentPhotoRequest request){
        HttpStatus httpCode;
        Response resp = new Response();
        AgentCategoryResponseDto response = service.setCategoryPhoto(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Image Saved !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
