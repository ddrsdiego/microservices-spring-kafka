package net.javaguides.service.order.services;

import net.javaguides.base.domains.commons.kafka.KafkaEnvelope;
import net.javaguides.service.order.models.Order;
import net.javaguides.service.order.repositories.OrderRepository;
import net.javaguides.service.order.services.kafka.OrderProducer;
import net.javaguides.service.order.wire.in.PlaceOrderRequest;
import net.javaguides.service.order.wire.out.PlaceOrderResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderProducer orderProducer;
    private final OrderRepository orderRepository;


    public OrderServiceImpl(OrderProducer orderProducer, OrderRepository orderRepository) {
        this.orderProducer = orderProducer;
        this.orderRepository = orderRepository;
    }

    @Override
    public PlaceOrderResponse placeOrder(PlaceOrderRequest request) {

        request.setOrderId(UUID.randomUUID().toString());

        Order newOrder = new Order();

        BeanUtils.copyProperties(request, newOrder);

        newOrder.setProductName(request.getName());
        newOrder.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        orderRepository.save(newOrder);

        PlaceOrderResponse response = new PlaceOrderResponse(newOrder.getOrderId().toString(),
                newOrder.getProductName(),
                newOrder.getQuantity(),
                newOrder.getPrice());

        KafkaEnvelope envelope = new KafkaEnvelope();
        envelope.setPayload(response);

        orderProducer.SendMessage(envelope);

        return response;
    }
}
