package com.sabi.agent.api.controller;


import com.sabi.agent.service.services.StateService;
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
@RequestMapping(Constants.APP_CONTENT +"sabi/" +"state")
public class StateController {


    private final StateService service;
    private final GlobalService globalService;

    public StateController(StateService service,GlobalService globalService) {
        this.service = service;
        this.globalService = globalService;
    }


    /** <summary>
     * State creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new states</remarks>
     */

//    @PostMapping("")
//    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
//    public ResponseEntity<Response> createState(@Validated @RequestBody  StateDto request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        StateResponseDto response = service.createState(request);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Successful");
//        resp.setData(response);
//        httpCode = HttpStatus.CREATED;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//
//
//    /** <summary>
//     * State update endpoint
//     * </summary>
//     * <remarks>this endpoint is responsible for updating states</remarks>
//     */
//
//    @PutMapping("")
//    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
//    public ResponseEntity<Response> updateState(@Validated @RequestBody  StateDto request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        StateResponseDto response = service.updateState(request);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Update Successful");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//
//
//    /** <summary>
//     * Get single record endpoint
//     * </summary>
//     * <remarks>this endpoint is responsible for getting a single record</remarks>
//     */
//    @GetMapping("/{id}")
//    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
//    public ResponseEntity<Response> getState(@PathVariable Long id){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        StateResponseDto response = service.findState(id);
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
//     * Get all records endpoint
//     * </summary>
//     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
//     */
//    @GetMapping("")
//    public ResponseEntity<Response> getStates(@RequestParam(value = "name",required = false)String name,
//                                             @RequestParam(value = "page") int page,
//                                             @RequestParam(value = "sortBy", required = false) String sort,
//                                             @RequestParam(value = "pageSize") int pageSize){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
//                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
//        Page<State> response = service.findAll(name, PageRequest.of(page, pageSize, sortType));
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//
//    /** <summary>
//     * Enable disenable
//     * </summary>
//     * <remarks>this endpoint is responsible for enabling and disenabling a State</remarks>
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
//    @GetMapping("/list")
//    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive",required = false)Boolean isActive,
//                                           @RequestParam(value = "countryId",required = false)Long countryId){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        List<State> response = service.getAll(isActive,countryId);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }






    @PostMapping("")
    public SingleResponse getState (SingleRequest request) throws Exception {
        SingleResponse response= globalService.getSingleState(request);
        return response;
    }




    @PostMapping("/page")
    public PageResponse getStates (BankRequest request) throws Exception {
        PageResponse response= globalService.getStatePagination(request);
        return response;
    }




    @PostMapping("/list")
    public ListResponse getAll (BankRequest request) throws Exception {
        ListResponse response= globalService.getStateList(request);
        return response;
    }

}
