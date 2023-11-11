package OrangeCorps.LBridge.DTO;

import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class UserDTO {
    private String userId;
    private String user_name;
    private String country;
    private String tel;
    private LocalDate birth;
    private String coupleId;
}