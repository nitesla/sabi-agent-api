package com.sabi.agent.api.controller;


import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.models.AuditTrail;
import com.sabi.framework.service.AsyncService;
import com.sabi.framework.service.AuditTrailService;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT +"audit")
public class AuditTrailController {

    @Autowired
    private AsyncService asyncService;
    private final AuditTrailService service;

    public AuditTrailController(AuditTrailService service) {
        this.service = service;
    }






    @GetMapping("/{id}")
    public ResponseEntity<Response> getAudit(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        AuditTrail response = service.getAudit(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @GetMapping("")
    public ResponseEntity<Response> audits(@RequestParam(value = "username", required = false) String username,
                                           @RequestParam(value = "event", required = false) String event,
                                           @RequestParam(value = "flag", required = false) String flag,
                                           @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                           @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                           @RequestParam(value = "page") int page,
                                           @RequestParam(value = "sortBy", required = false) String sort,
                                           @RequestParam(value = "pageSize") int pageSize) {

        HttpStatus httpCode;
        Response resp = new Response();
        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        Page<AuditTrail> response = service.findAll(username,event,flag, startDate, endDate, PageRequest.of(page, pageSize, sortType));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }
}