package com.sabi.agent.api.controller;

import com.sabi.agent.core.dto.requestDto.WishListDto;
import com.sabi.agent.core.dto.responseDto.WishListResponseDto;
import com.sabi.agent.core.models.WishList;
import com.sabi.agent.service.services.WishListService;
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

@SuppressWarnings("All")
@Valid
@RestController
@RequestMapping(Constants.APP_CONTENT +"wishlist")
public class WishListController {

    private final WishListService service;

    public WishListController(WishListService service) {
        this.service = service;
    }

    /** <summary>
     *  wish list creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Wish list</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createWishList(@Validated @RequestBody WishListDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        WishListResponseDto response = service.createWishList(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * wish list update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating wish list</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateWishLIst(@Validated @RequestBody  WishListDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        WishListResponseDto response = service.updateWishList(request);
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
    public ResponseEntity<Response> getWishList(@Validated @PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        WishListResponseDto response = service.findWishList(id);
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
    public ResponseEntity<Response> getWishLists(@RequestParam(value = "agentId",required = false)String agentId,
                                             @RequestParam(value = "productId",required = false)String productId,
                                             @RequestParam(value = "productName",required = false)String productName,
                                             @RequestParam(value = "picture",required = false)String picture,
                                             @RequestParam(value = "page") int page,
                                             @RequestParam(value = "sortBy", required = false) String sort,
                                             @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Sort sortType = sort.equalsIgnoreCase("asc") ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        Page<WishList> response = service.findAll(agentId, productId, productName,picture ,PageRequest.of(page, pageSize, sortType));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Delete a wish list record
     * </summary>
     * <remarks>this endpoint is responsible for deleting a wish list record</remarks>
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteWishList(@Validated @PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        WishListResponseDto response = service.deleteWishList(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record deleted successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
    
}
