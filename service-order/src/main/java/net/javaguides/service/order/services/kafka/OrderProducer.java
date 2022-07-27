package net.javaguides.service.order.services.kafka;

import net.javaguides.base.domains.commons.kafka.KafkaEnvelope;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(net.javaguides.service.order.services.kafka.OrderProducer.class);

    private NewTopic topic;
    private KafkaTemplate<String, KafkaEnvelope> kafkaTemplate;

    public OrderProducer(NewTopic topic, KafkaTemplate<String, KafkaEnvelope> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void SendMessage(KafkaEnvelope envelope) {

        var name = envelope.getClass().getName();

        LOGGER.info(String.format("Order event => %s", envelope.toString()));

        Message<KafkaEnvelope> message = MessageBuilder
                .withPayload(envelope)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }
}