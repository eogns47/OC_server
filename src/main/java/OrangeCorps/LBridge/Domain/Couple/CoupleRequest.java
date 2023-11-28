package OrangeCorps.LBridge.Domain.Couple;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CoupleRequest {

    @Id
    @Column(length=256)
    private String uuid;
    @Column(length=256)
    private String coupleId;


}
