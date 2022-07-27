package net.javaguides.base.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class OrderDto {
    private String orderId;
    private String name;
    private int quantity;
    private double price;
}

