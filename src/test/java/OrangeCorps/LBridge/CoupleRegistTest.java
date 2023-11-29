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

    @Test
    @DisplayName("서로 요청 보낼 시 등록 되는지 테스트")
    void testRequestEachOther(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid("test1");
        User user = new User(userDTO);

        userDTO.setUuid("test2");
        User user2 = new User(userDTO);

        userRepository.save(user);
        userRepository.save(user2);


        CoupleRequestDTO coupleRequestDTO = new CoupleRequestDTO(user.getUuid(),user2.getUuid());
        CoupleRequestDTO coupleRequestDTO2 = new CoupleRequestDTO(user2.getUuid(),user.getUuid());


        String result = coupleRequestService.sendCoupleRequest(coupleRequestDTO);
        String result2 = coupleRequestService.sendCoupleRequest(coupleRequestDTO2);

        assertEquals(COUPLE_REGISTRATION_REQUEST_SUCCESS, result);
        assertEquals(COUPLE_LINK_SUCCESS, result2);


        assertEquals(userRepository.findByUuid(user.getUuid()).get().getCoupleId(),user2.getUuid());

    }

}
