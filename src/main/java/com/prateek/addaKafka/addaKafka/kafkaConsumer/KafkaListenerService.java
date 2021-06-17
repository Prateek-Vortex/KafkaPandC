package com.prateek.addaKafka.addaKafka.kafkaConsumer;

import com.prateek.addaKafka.addaKafka.model.UserPublishModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    @Autowired
    KafkaRepository kafkaRepository;

    @KafkaListener(topics = "NewTopic",groupId = "group_json",
            containerFactory = "userKafkaListenerFactory")
    public UserPublishModel consumeJson(UserPublishModel userPublishModel) {
        return userPublishModel;
    }

    public void saveToDB(UserPublishModel userPublishModel){
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        KafkaEntity kafkaEntity=modelMapper.map(userPublishModel, KafkaEntity.class);
        kafkaRepository.save(kafkaEntity);
    }

}
