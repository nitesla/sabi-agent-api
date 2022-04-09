package com.sabi.agent.api.controller;


import com.sabi.agent.core.dto.requestDto.EnableDisEnableDto;
import com.sabi.agent.core.dto.requestDto.UserTaskDto;
import com.sabi.agent.core.dto.responseDto.UserTaskResponseDto;
import com.sabi.agent.core.models.UserTask;
import com.sabi.agent.service.services.UserTaskService;
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
import java.util.Date;
import java.util.List;

@SuppressWarnings("All")
@RestController
@Valid
@RequestMapping(Constants.APP_CONTENT +"userTask")
public class UserTaskController {

    private final UserTaskService service;

    public UserTaskController(UserTaskService service) {
        this.service = service;
    }

    /** <summary>
     *  UserTask creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new UserTask</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createUserTask(@Validated @RequestBody UserTaskDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        UserTaskResponseDto response = service.createUserTask(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * UserTask update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating UserTask</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateUserTask(@Validated @RequestBody  UserTaskDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        UserTaskResponseDto response = service.updateUserTask(request);
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
    public ResponseEntity<Response> getUserTask(@Validated @PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        UserTaskResponseDto response = service.findUserTask(id);
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
    public ResponseEntity<Response> getUserTasks(@RequestParam(value = "endDate",required = false) Date endDate,
                                                 @RequestParam(value = "dateAssigned",required = false)Date dateAssigned,
                                                 @RequestParam(value = "status",required = false) String status,
                                                 @RequestParam(value = "userId",required = false) Long userId,
                                                 @RequestParam(value = "page") int page,
                                                 @RequestParam(value = "sortBy", required = false) String sort,
                                                 @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        Page<UserTask> response = service.findAll(endDate, dateAssigned, status, userId, PageRequest.of(page, pageSize, sortType));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Enable disable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a UserTask</remarks>
     */

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisableUserTask(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllByStatus(@Validated @RequestParam(value = "isActive") Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<UserTask> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
