package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.agentDto.requestDto.AgentCategoryTaskDto;
import com.sabi.agent.core.dto.requestDto.EnableDisEnableDto;
import com.sabi.agent.core.dto.responseDto.AgentCategoryTaskResponseDto;
import com.sabi.agent.core.models.agentModel.AgentCategoryTask;
import com.sabi.agent.service.services.AgentCategoryTaskService;
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
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@SuppressWarnings("All")
@RestController
@Validated
@Valid
@RequestMapping(Constants.APP_CONTENT +"agentCategoryTask")
public class AgentCategoryTaskController {

    private final AgentCategoryTaskService service;

    public AgentCategoryTaskController(AgentCategoryTaskService service) {
        this.service = service;
    }

    /** <summary>
     *  Agent Category Task creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Agent Category Task</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createAgentCategoryTask(@Validated @RequestBody AgentCategoryTaskDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        AgentCategoryTaskResponseDto response = service.createAgentCategoryTask(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Agent Category Task update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating Agent Category Task</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateAgentCategoryTask(@Validated @RequestBody  AgentCategoryTaskDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        AgentCategoryTaskResponseDto response = service.updateAgentCategoryTask(request);
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
    public ResponseEntity<Response> getAgentCategoryTask(@Validated @PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        AgentCategoryTaskResponseDto response = service.findAgentCategoryTask(id);
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
    public ResponseEntity<Response> getAgentCategoryTasks(@RequestParam(value = "name",required = false)String name,
                                             @RequestParam(value = "isActive",required = false)Boolean isActive,
                                              @RequestParam(value = "page") int page,
                                               @RequestParam(value = "taskId", required = false) Long taskId,
                                               @RequestParam(value = "agentCategoryId", required = false) Long agentCategoryId,
//                                              @RequestParam(value = "agentCategoryName", required = false) String agentCategoryName,
                                              @RequestParam(value = "sortBy", required = false) String sort,
                                                          @RequestParam(value = "fromDate", required = false) String fromDate,
                                                          @RequestParam(value = "toDate", required = false) String toDate,
                                              @RequestParam(value = "pageSize") int pageSize){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate fromLocalDate = fromDate != null ? LocalDate.parse(fromDate, formatter) : null;
            LocalDate toLocalDate = toDate !=null ? LocalDate.parse(toDate, formatter): null;
        HttpStatus httpCode ;
        Response resp = new Response();
        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        Page<AgentCategoryTask> response = service.findAll(name, agentCategoryId,
                taskId, isActive, fromLocalDate, toLocalDate, PageRequest.of(page, pageSize, sortType));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a Agent Category Task</remarks>
     */

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisableAgtCatTask(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllByStatus(@Validated @RequestParam(value = "isActive") Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<AgentCategoryTask> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/agentCategory/{agentCategoryId}")
    public ResponseEntity<Response> getByAgentCategoryId(@PathVariable Long agentCategoryId){
        Response resp = new Response();
        List<AgentCategoryTask> response = service.getByAgentCategoryId(agentCategoryId);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


}
