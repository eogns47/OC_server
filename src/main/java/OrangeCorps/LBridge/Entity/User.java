package OrangeCorps.LBridge.Entity;

import OrangeCorps.LBridge.DTO.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="User") // 미 사용시 클래스이름 == 테이블이름
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(length=128)
    private String userId;

    @Column(length=128)
    private String user_name;

    @Column(length=128)
    private String country;

    @Column(length=32)
    private String tel;

    @Column
    private LocalDate birth;

    @Column(length=128)
    private String coupleId;

    public User(UserDTO userDTO){
        userId = userDTO.getUserId();
        user_name = userDTO.getUser_name();
        country = userDTO.getCountry();
        tel = userDTO.getTel();
        birth = userDTO.getBirth();
        coupleId = userDTO.getCoupleId();
    }

    public void updateCoupleId(String newCoupleId) {
        this.coupleId = newCoupleId;
        return;
    }

}
