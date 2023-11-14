package OrangeCorps.LBridge.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TIDAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;
    private Long questionId;
    private String answer;
    private LocalDateTime timestamp;
    private String userId;
    private String coupleId;
    private String combinedKey;

    public TIDAnswer() {
        this.timestamp = LocalDateTime.now();
        initCombinedKey();
    }

    private void initCombinedKey() {
        String year = String.valueOf(timestamp.getYear());
        String month = String.format("%02d", timestamp.getMonthValue());
        String day = String.format("%02d", timestamp.getDayOfMonth());

        this.combinedKey = year + "_" + month + "_" + day + "_" + userId + "_" + coupleId;
    }
}