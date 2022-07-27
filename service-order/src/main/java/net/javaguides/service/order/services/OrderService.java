package net.javaguides.service.order.services;

import net.javaguides.service.order.wire.in.PlaceOrderRequest;
import net.javaguides.service.order.wire.out.PlaceOrderResponse;

public interface OrderService {
    PlaceOrderResponse placeOrder(PlaceOrderRequest request);
}

