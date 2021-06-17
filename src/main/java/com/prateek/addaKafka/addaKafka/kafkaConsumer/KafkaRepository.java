package com.prateek.addaKafka.addaKafka.kafkaConsumer;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


public interface KafkaRepository extends CrudRepository<KafkaEntity,Long> {
}
