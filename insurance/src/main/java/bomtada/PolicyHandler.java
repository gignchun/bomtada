package bomtada;

import bomtada.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired HumanResourcesRepository humanResourcesRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverJoined_Register(@Payload Joined joined){

        if(joined.isMe()){
            System.out.println("##### listener Register : " + joined.toJson());

            HumanResources humanResources = new HumanResources();
            humanResources.setAppId(joined.getId());
            humanResources.setName(joined.getName());
            humanResources.setStatus("join complete");
            humanResourcesRepository.save(humanResources);
        }
    }


}
