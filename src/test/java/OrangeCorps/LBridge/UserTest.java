package OrangeCorps.LBridge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import OrangeCorps.LBridge.Domain.Couple.CoupleRequestDTO;
import OrangeCorps.LBridge.Domain.User.UserDTO;
import OrangeCorps.LBridge.Domain.User.User;
import OrangeCorps.LBridge.Domain.User.UserRepository;
import OrangeCorps.LBridge.Service.CoupleService.CoupleRegistService;
import OrangeCorps.LBridge.Service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;


@SpringBootTest
@Transactional
public class UserTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private CoupleRegistService coupleRegistService;

    @Test
    @DisplayName("user가 정상적으로 등록되는지 테스트")
    void testUserLogin() {

        UserDTO userDTO = new UserDTO();
        userDTO.setUuid("test22");

        User user = new User(userDTO);

        userRepository.save(user);

        // 데이터베이스에 해당 유저가 존재하는지 확인
        boolean userExists = userRepository.existsById("test22");

        // 해당 유저가 존재해야 테스트가 성공
        assertTrue(userExists, "User should exist in the database");
    }

    @Test
    @DisplayName("user가 userId(기본키)가 없을 때 등록되지 않는지 테스트")
    void testUserLoginWithoutUserId() {
        UserDTO userDTO = new UserDTO();
        // userId를 설정하지 않음

        User user = new User(userDTO);

        // 예외(DataIntegrityViolationException)이 발생해야 함
        assertThrows(JpaSystemException.class, () -> userRepository.save(user),
                "User should not be saved without userId");
    }

    @Test
    @DisplayName("couple이 잘 등록되는지 테스트")
    void testCoupleRegist(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid("test1");
        User user1 = new User(userDTO);
        userDTO.setUuid("test2");
        User user2 = new User(userDTO);

        userRepository.save(user1);
        userRepository.save(user2);

        CoupleRequestDTO coupleRequestDTO = new CoupleRequestDTO("test1","test2");

        coupleRegistService.registCouple(coupleRequestDTO);



        assertEquals(userRepository.findByUuid("test1").get().getCoupleId(),user2.getUuid());
    }

    @Test
    @DisplayName("coupleId가 존재하지 않을 때 등록되지 않는지 테스트")
    void testIsNotRegistWhenCoupleNotExist(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid("test1");
        User user1 = new User(userDTO);

        userRepository.save(user1);

        CoupleRequestDTO coupleRequestDTO = new CoupleRequestDTO("test1","test2");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            coupleRegistService.linkCouple(coupleRequestDTO);
        });

        assertEquals(user1.getCoupleId(),null);

    }

    @Test
    @DisplayName("내 coupleId 반환 테스트")
    void testCoupleExist(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid("test1");
        User user1 = new User(userDTO);
        userDTO.setUuid("test2");
        User user2 = new User(userDTO);
        userDTO.setUuid("test3");
        User user3 = new User(userDTO);


        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        CoupleRequestDTO coupleRequestDTO = new CoupleRequestDTO("test1","test2");

        coupleRegistService.registCouple(coupleRequestDTO);


        assertEquals(userService.getCoupleIdByUuid("test1"),user2.getUuid());
        assertEquals(userService.coupleExist("test1"),true);
        assertEquals(userService.coupleExist("test3"),false);
    }

    @Test
    @DisplayName("내 커플 정보 API 테스트")
    void testMyCoupleAPI(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid("test1");
        User user1 = new User(userDTO);
        userDTO.setUuid("test2");
        User user2 = new User(userDTO);
        userRepository.save(user1);
        userRepository.save(user2);
        CoupleRequestDTO coupleRequestDTO = new CoupleRequestDTO("test1","test2");


        coupleRegistService.registCouple(coupleRequestDTO);


        assertEquals(userService.myCouple(user1.getUuid()).getUuid(),user2.getUuid());
    }




}
