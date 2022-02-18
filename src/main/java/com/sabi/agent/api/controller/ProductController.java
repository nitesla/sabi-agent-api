package com.sabi.agent.api.controller;


import com.sabi.agent.core.integrations.request.AllProductsRequest;
import com.sabi.agent.core.integrations.request.SingleProductRequest;
import com.sabi.agent.core.integrations.response.SingleProductResponse;
import com.sabi.agent.core.integrations.response.product.AllProductResponse;
import com.sabi.agent.service.integrations.ProductService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@SuppressWarnings("All")
@Slf4j
@RestController
@RequestMapping(Constants.APP_CONTENT + "product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }


    /**
     * <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
    @PostMapping("")
    public SingleProductResponse productDetails(@RequestBody SingleProductRequest request) throws Exception {
        SingleProductResponse response = service.productDetail(request);
        return response;
    }


    /**
     * <summary>
     * Get all records endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
     */
    @GetMapping("/list")
    public AllProductResponse allProductDetails(@RequestParam(value = "pageNumber", required = false) Integer page,
                                                @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                @RequestParam(value = "searchString", required = false) String searchString,
                                                @RequestParam(value = "state", required = false) String state,
                                                @RequestParam(value = "direction", required = false) String direction,
                                                @RequestParam(value = "sort", required = false) String sort
    ) throws Exception {
        AllProductsRequest request = new AllProductsRequest();
        request.setPage(page);
        request.setPageSize(pageSize);
        request.setDirection(direction);
        request.setSearchString(searchString);
        request.setSortBy(sort);
        request.setState(state);
        AllProductResponse response = service.allProductDetail(request);
        return response;
    }

    @GetMapping("/merchant/category")
    public ResponseEntity<Response> merchantProductCategory() throws IOException {
        List merchantProductCategories = service.getMerchantProductCategory();
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(merchantProductCategories);
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