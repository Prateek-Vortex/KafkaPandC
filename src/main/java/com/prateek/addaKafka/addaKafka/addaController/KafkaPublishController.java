package com.prateek.addaKafka.addaKafka.addaController;

import com.prateek.addaKafka.addaKafka.kafkaConsumer.KafkaEntity;
import com.prateek.addaKafka.addaKafka.kafkaConsumer.KafkaListenerService;
import com.prateek.addaKafka.addaKafka.kafkaConsumer.KafkaRepository;
import com.prateek.addaKafka.addaKafka.model.UserPublishModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class KafkaPublishController {

    @Autowired
    KafkaTemplate<String, UserPublishModel> kafkaTemplate;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate2;

    @Autowired
    KafkaRepository kafkaRepository;

    private String TOPIC="NewTopic";


    @GetMapping("publish/{message}")
    public String publishMessage(@PathVariable("message") String message){

        kafkaTemplate2.send(TOPIC,message);

        return "Published";
    }

    @PostMapping("publish/message")
    public UserPublishModel publishJsonMessage(@RequestBody UserPublishModel userPublishModel){
        kafkaTemplate.send(TOPIC,userPublishModel);
        KafkaListenerService kafkaListenerService=new KafkaListenerService();
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        KafkaEntity kafkaEntity=modelMapper.map(userPublishModel, KafkaEntity.class);
        kafkaRepository.save(kafkaEntity);
        //kafkaListenerService.saveToDB(userPublishModel);
        return kafkaListenerService.consumeJson(userPublishModel);
    }
}
