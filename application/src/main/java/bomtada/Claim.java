package bomtada;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Claim_table")
public class Claim {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long memberId;
    private String result;
    private Long amount;

    @PostPersist
    public void onPostPersist(){
        Requested requested = new Requested();
        BeanUtils.copyProperties(this, requested);
        requested.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        bomtada.external.Payment payment = new bomtada.external.Payment();
        // mappings goes here
        payment.setClaimId(this.getId());
        payment.setMemberId(this.getMemberId());
        //payment.setAmount(500l);
        payment.setResult("Payment Completed");

        ApplicationApplication.applicationContext.getBean(bomtada.external.PaymentService.class)
            .process(payment);


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }




}
