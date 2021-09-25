package com.sabi.agent.api.controller;


import com.sabi.agent.core.integrations.order.*;
import com.sabi.agent.core.integrations.order.orderResponse.CreateOrderResponse;
import com.sabi.agent.service.integrations.OrderService;
import com.sabi.framework.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
}
