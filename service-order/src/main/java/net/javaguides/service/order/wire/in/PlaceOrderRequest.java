package net.javaguides.service.order.wire.in;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderRequest {
    private String orderId;
    private String name;
    private int quantity;
    private double price;
}
