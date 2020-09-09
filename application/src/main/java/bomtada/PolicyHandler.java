package bomtada;

import bomtada.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class PolicyHandler{

    @Autowired ClaimRepository claimRepository;
    @Autowired ApplicationRepository applicationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverHumanResourcesRegistered(@Payload Registered registered)
    {
        System.out.println("##### listener test : " + registered.toJson());
        System.out.println("##### listener appId : " + registered.getAppId());

        /*Iterator<Application> iterator = applicationRepository.findAll().iterator();
        while(iterator.hasNext()){
            Application notice = iterator.next();
            System.out.println("##### listener Send : " + notice.getId());

        }*/

        if(registered.isMe()){
            Optional<Application> applicationOptional = applicationRepository.findById(registered.getAppId());
            Application application = applicationOptional.get();
            System.out.println("##### listener getId : " + application.getId());
            application.setMemberId(registered.getId());
            applicationRepository.save(application);
        }

    }
/*
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRegistered_Update(@Payload Registered registered){

        if(registered.isMe()){
            System.out.println("##### listener Update : " + registered.toJson());
        }
    }
*/
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverProcessed(@Payload Processed processed)
    {
        if(processed.isMe()){
            Optional<Claim> claimOptional = claimRepository.findById(processed.getClaimId());
            Claim claim = claimOptional.get();
            claim.setResult(processed.getResult());
            claim.setAmount(processed.getAmount());
            claimRepository.save(claim);
        }

    }


}
