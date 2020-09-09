package bomtada;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Dashboard_table")
public class Dashboard {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long memberId;
        private String status;
        private String result;
        private Long amount;


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
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
