package com.sabi.agent.api.controller;


import com.sabi.agent.service.services.CountryService;
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
@RequestMapping(Constants.APP_CONTENT +"sabi/"+"country")
public class CountryController {

    private final CountryService service;
    private final GlobalService globalService;

    public CountryController(CountryService service,GlobalService globalService) {
        this.service = service;
        this.globalService = globalService;
    }


    /** <summary>
     * Country creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new country</remarks>
     */

//    @PostMapping("")
//    public ResponseEntity<Response> createCountry(@Validated @RequestBody CountryDto request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        CountryResponseDto response = service.createCountry(request);
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
//     * Country update endpoint
//     * </summary>
//     * <remarks>this endpoint is responsible for updating countries</remarks>
//     */
//
//    @PutMapping("")
//    public ResponseEntity<Response> updateCountry(@Validated @RequestBody  CountryDto request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        CountryResponseDto response = service.updateCountry(request);
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
//    public ResponseEntity<Response> getCountry(@PathVariable Long id){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        CountryResponseDto response = service.findCountry(id);
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
//    public ResponseEntity<Response> getCountries(@RequestParam(value = "name",required = false)String name,
//                                              @RequestParam(value = "code",required = false)String code,
//                                              @RequestParam(value = "page") int page,
//                                              @RequestParam(value = "sortBy", required = false) String sort,
//                                              @RequestParam(value = "pageSize") int pageSize){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
//                ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
//        Page<Country> response = service.findAll(name,code, PageRequest.of(page, pageSize, sortType));
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
//    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive",required = false)Boolean isActive){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        List<Country> response = service.getAll(isActive);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }



    @PostMapping("")
    public SingleResponse getCountry (SingleRequest request) throws Exception {
        SingleResponse response= globalService.getSingleCountry(request);
        return response;
    }


    @PostMapping("/page")
    public PageResponse getCountries (BankRequest request) throws Exception {
        PageResponse response= globalService.getCountryPagination(request);
        return response;
    }





    @PostMapping("/list")
    public ListResponse getAll (BankRequest request) throws Exception {
        ListResponse response= globalService.getCountryList(request);
        return response;
    }

}
