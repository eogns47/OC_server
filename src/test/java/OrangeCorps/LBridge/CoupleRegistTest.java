package OrangeCorps.LBridge;

import OrangeCorps.LBridge.Domain.Couple.CoupleRequestDTO;
import OrangeCorps.LBridge.Domain.Couple.CoupleRequestRepository;
import OrangeCorps.LBridge.Domain.User.UserDTO;
import OrangeCorps.LBridge.Domain.User.UserRepository;
import OrangeCorps.LBridge.Service.CoupleService.CoupleRequestService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import OrangeCorps.LBridge.Domain.User.User;
import static OrangeCorps.LBridge.Config.Config.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class CoupleRegistTest {
    @Autowired
    CoupleRequestService coupleRequestService;

    @Autowired
    CoupleRequestRepository coupleRequestRepository;

    @Autowired
    UserRepository userRepository;




    @Test
    @DisplayName("커플 요청 등록 테스트.")
    void requestTest(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid("test1");
        User user = new User(userDTO);

        userDTO.setUuid("test2");
        User user2 = new User(userDTO);

        userRepository.save(user);
        userRepository.save(user2);


        CoupleRequestDTO coupleRequestDTO = new CoupleRequestDTO(user.getUuid(),user2.getUuid());
        CoupleRequestDTO coupleRequestDTOFalse = new CoupleRequestDTO(user.getUuid(),"nothing");

        String result = coupleRequestService.sendCoupleRequest(coupleRequestDTO);

        assertEquals(COUPLE_REGISTRATION_REQUEST_SUCCESS, result);

    }

}
