package com.sabi.agent.api.controller;


import com.sabi.agent.core.integrations.request.AllProductsRequest;
import com.sabi.agent.core.integrations.request.SingleProductRequest;
import com.sabi.agent.core.integrations.response.MerchantProductCategory;
import com.sabi.agent.core.integrations.response.SingleProductResponse;
import com.sabi.agent.core.integrations.response.product.AllProductResponse;
import com.sabi.agent.service.integrations.ProductService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@SuppressWarnings("All")
@Slf4j
@RestController
@RequestMapping(Constants.APP_CONTENT +"product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }


    /** <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
    @PostMapping("")
    public SingleProductResponse productDetails (@RequestBody SingleProductRequest request) throws Exception {
        SingleProductResponse response= service.productDetail(request);
        return response;
    }


    /** <summary>
     * Get all records endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
     */
    @GetMapping("/list")
    public AllProductResponse allProductDetails (AllProductsRequest request) throws Exception {
        AllProductResponse response= service.allProductDetail(request);
        return response;
    }

    @GetMapping("/merchant/category")
    public ResponseEntity<Response>  merchantProductCategory (@RequestParam(value = "page") int page,
                                                                  @RequestParam(value = "sortBy", required = false) String sort,
                                                                  @RequestParam(value = "pageSize") int pageSize) throws IOException {
        List<MerchantProductCategory> merchantProductCategories = service.getMerchantProductCategory();
        Response response = new Response();
        Sort sortType = (sort != null && sort.equalsIgnoreCase("asc"))
                ? Sort.by(Sort.Order.asc("id")) : Sort.by(Sort.Order.desc("id"));
        Pageable paging = PageRequest.of(page, pageSize, sortType);
        int start = Math.min((int)paging.getOffset(), merchantProductCategories.size());
        int end = Math.min((start + paging.getPageSize()), merchantProductCategories.size());

        Page<MerchantProductCategory> pagedResponse = new PageImpl<>(merchantProductCategories.subList(start, end), paging, merchantProductCategories.size());
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(pagedResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<AllProductResponse> getProductByCategoryId(@PathVariable String id,
                                                                     @RequestParam(value = "direction") String direction,
                                                                     @RequestParam(value = "page", required = false) Integer page,
                                                                     @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                                     @RequestParam(value = "sortBy") String sortBy,
                                                                     @RequestParam(value = "state", required = false) String state) throws IOException {
        AllProductResponse response = service.getProductById(id, direction, page, pageSize, sortBy, state);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}