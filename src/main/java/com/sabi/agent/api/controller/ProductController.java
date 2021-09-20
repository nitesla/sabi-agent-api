package com.sabi.agent.api.controller;


import com.sabi.agent.core.integrations.request.AllProductsRequest;
import com.sabi.agent.core.integrations.request.SingleProductRequest;
import com.sabi.agent.core.integrations.response.AllProductResponse;
import com.sabi.agent.core.integrations.response.SingleProductResponse;
import com.sabi.agent.service.integrations.ProductService;
import com.sabi.framework.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


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
}
