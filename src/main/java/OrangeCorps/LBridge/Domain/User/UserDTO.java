package OrangeCorps.LBridge.Domain.User;

import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class UserDTO {
    private String uuid;
    private String name;
    private String country;
    private LocalDate birth;
    private String coupleId;
}