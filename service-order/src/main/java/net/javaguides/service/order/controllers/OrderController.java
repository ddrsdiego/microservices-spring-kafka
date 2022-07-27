package net.javaguides.service.order.controllers;


import net.javaguides.service.order.services.OrderService;
import net.javaguides.service.order.wire.in.PlaceOrderRequest;
import net.javaguides.service.order.wire.out.PlaceOrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<PlaceOrderResponse> placeOrder(@RequestBody PlaceOrderRequest request) {

        PlaceOrderResponse response =
                this.orderService.placeOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}