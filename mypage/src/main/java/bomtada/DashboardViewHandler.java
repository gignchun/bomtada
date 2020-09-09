package bomtada;

import bomtada.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardViewHandler {


    @Autowired
    private DashboardRepository dashboardRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRegistered_then_CREATE_1 (@Payload Registered registered) {
        try {
            if (registered.isMe()) {
                // view 객체 생성
                Dashboard dashboard = new Dashboard();
                // view 객체에 이벤트의 Value 를 set 함
                dashboard.setMemberId(registered.getId());
                dashboard.setStatus(registered.getStatus());
                // view 레파지 토리에 save
                dashboardRepository.save(dashboard);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenProcessed_then_CREATE_2 (@Payload Processed processed) {
        try {
            if (processed.isMe()) {
                // view 객체 생성
                Dashboard dashboard = new Dashboard();
                // view 객체에 이벤트의 Value 를 set 함
                dashboard.setMemberId(processed.getMemberId());
                dashboard.setResult(processed.getResult());
                dashboard.setAmount(processed.getAmount());
                // view 레파지 토리에 save
                dashboardRepository.save(dashboard);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @StreamListener(KafkaProcessor.INPUT)
    public void whenRequested_then_DELETE_1(@Payload Requested requested) {
        try {
            if (requested.isMe()) {
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}