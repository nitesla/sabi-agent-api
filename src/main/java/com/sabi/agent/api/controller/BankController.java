package com.sabi.agent.api.controller;

import com.sabi.agent.service.services.BankService;
import com.sabi.framework.globaladminintegration.GlobalService;
import com.sabi.framework.globaladminintegration.request.BankRequest;
import com.sabi.framework.globaladminintegration.request.SingleRequest;
import com.sabi.framework.globaladminintegration.response.ListResponse;
import com.sabi.framework.globaladminintegration.response.PageResponse;
import com.sabi.framework.globaladminintegration.response.SingleResponse;
import com.sabi.framework.utils.Constants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT +"bank")
public class BankController {


    private final BankService service;
    private final GlobalService globalService;

    public BankController(BankService service,GlobalService globalService) {
        this.service = service;
        this.globalService = globalService;
    }


    /** <summary>
     * Bank creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new bank</remarks>
     */

//    @PostMapping("")
//    public ResponseEntity<Response> createBank(@Validated @RequestBody BankDto request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        BankResponseDto response = service.createBank(request);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Successful");
//        resp.setData(response);
//        httpCode = HttpStatus.CREATED;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//
//    /** <summary>
//     * Bank update endpoint
//     * </summary>
//     * <remarks>this endpoint is responsible for updating bank</remarks>
//     */
//
//    @PutMapping("")
//    public ResponseEntity<Response> updateBank(@Validated @RequestBody  BankDto request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        BankResponseDto response = service.updateBank(request);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Update Successful");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//
//    /** <summary>
//     * Get single record endpoint
//     * </summary>
//     * <remarks>this endpoint is responsible for getting a single record</remarks>
//     */
//    @GetMapping("/{id}")
//    public ResponseEntity<Response> getBank(@PathVariable Long id){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        BankResponseDto response = service.findBank(id);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//
//    /** <summary>
//     * Get all records endpoint
//     * </summary>
//     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
//     */
//    @GetMapping("")
//    public ResponseEntity<Response> getBanks(@RequestParam(value = "name",required = false)String name,
//                                              @RequestParam(value = "bankCode",required = false)String bankCode,
//                                              @RequestParam(value = "page") int page,
//                                             @RequestParam(value = "sortBy", required = false) String sort,
//                                              @RequestParam(value = "pageSize") int pageSize){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
//                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
//        Page<Bank> response = service.findAll(name,bankCode, PageRequest.of(page, pageSize, sortType));
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//
//
//    /** <summary>
//     * Enable disenable
//     * </summary>
//     * <remarks>this endpoint is responsible for enabling and disenabling a bank</remarks>
//     */
//
//    @PutMapping("/enabledisenable")
//    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisEnableDto request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        service.enableDisEnableState(request);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Successful");
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//
//
//    @GetMapping("/list")
//    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        List<Bank> response = service.getAll(isActive);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }




    @PostMapping("")
    public SingleResponse getBank (SingleRequest request) throws Exception {
        SingleResponse response= globalService.getSingleBank(request);
        return response;
    }


    @PostMapping("/page")
    public PageResponse getBanks (BankRequest request) throws Exception {
        PageResponse response= globalService.getBankPagination(request);
        return response;
    }

    @PostMapping("/list")
    public ListResponse getAll (BankRequest request) throws Exception {
        ListResponse response= globalService.getBankList(request);
        return response;
    }
}
