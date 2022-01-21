package com.sabi.agent.api.controller;


import com.sabi.agent.core.integrations.order.*;
import com.sabi.agent.core.integrations.order.orderResponse.CreateOrderResponse;
import com.sabi.agent.core.integrations.request.MerchBuyRequest;
import com.sabi.agent.core.integrations.response.MerchBuyResponse;
import com.sabi.agent.core.models.AgentOrder;
import com.sabi.agent.service.integrations.OrderService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@SuppressWarnings("All")
@Slf4j
@RestController
@RequestMapping(Constants.APP_CONTENT +"order")
public class OrderController {


    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }




    /** <summary>
         * Place order endpoint
     * </summary>
     * <remarks>this endpoint is responsible for placing order</remarks>
     */
    @PostMapping("/process")
    public CreateOrderResponse placeOrder (@RequestBody PlaceOrder request) throws Exception {
        CreateOrderResponse response= service.placeOrder(request);
        return response;
    }



    /** <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
    @PostMapping("")
    public SingleOrderResponse orderDetails (@RequestBody SingleOrderRequest request) throws Exception {
        SingleOrderResponse response= service.orderDetail(request);
        return response;
    }


    /** <summary>
     * Get Order history
     * </summary>
     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
     */
    @GetMapping("/list")
    public OrderHistoryResponse orderHistory (OrderHistoryRequest request) throws Exception {
        OrderHistoryResponse response= service.orderHistory(request);
        return response;
    }

    @PostMapping("/merchBuy")
    public MerchBuyResponse merchBuy(@RequestBody MerchBuyRequest request){
        return service.merchBuy(request);
    }



    @GetMapping("")
    public ResponseEntity<Response> getOrders(@RequestParam(value = "orderId",required = false)Long orderId,
                                              @RequestParam(value = "status",required = false)Boolean status,
                                              @RequestParam(value = "createdDate",required = false)Date createdDate,
                                              @RequestParam(value = "agentId",required = false)Long agentId,
                                              @RequestParam(value = "userName", required = false) String userName,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "sortBy", required = false) String sort,
                                              @RequestParam(value = "pageSize") int pageSize){

        HttpStatus httpCode ;
        Response resp = new Response();
        Sort sortType = sort.equalsIgnoreCase("asc") ?  Sort.by(Sort.Order.asc("id")) :   Sort.by(Sort.Order.desc("id"));
        Page<AgentOrder> response = service.findAll(orderId,status,createdDate,agentId, userName,PageRequest.of(page, pageSize, sortType));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
