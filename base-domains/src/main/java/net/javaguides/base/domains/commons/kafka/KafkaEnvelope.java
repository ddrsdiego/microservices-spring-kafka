package net.javaguides.base.domains.commons.kafka;

import lombok.Data;

@Data
public class KafkaEnvelope {
    private Object payload;
}
