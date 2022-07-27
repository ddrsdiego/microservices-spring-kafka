package net.javaguides.service.order.wire.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderResponse {
    private String orderId;
    private String name;
    private int quantity;
    private double price;
}
