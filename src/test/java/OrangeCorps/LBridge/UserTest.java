package OrangeCorps.LBridge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import OrangeCorps.LBridge.Domain.User.UserDTO;
import OrangeCorps.LBridge.Domain.User.User;
import OrangeCorps.LBridge.Domain.User.UserRepository;
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

    @Test
    @DisplayName("user가 정상적으로 등록되는지 테스트")
    void testUserLogin() {

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("test22");

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
        userDTO.setUserId("test1");
        User user1 = new User(userDTO);
        userDTO.setUserId("test2");
        User user2 = new User(userDTO);

        userRepository.save(user1);
        userRepository.save(user2);

        userService.linkCouple("test1","test2");



        assertEquals(userRepository.findByUserId("test1").get().getCoupleId(),user2.getUserId());
    }

    @Test
    @DisplayName("coupleId가 존재하지 않을 때 등록되지 않는지 테스트")
    void testIsNotRegistWhenCoupleNotExist(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("test1");
        User user1 = new User(userDTO);

        userRepository.save(user1);

        userService.linkCouple("test1","test2");

        assertEquals(user1.getCoupleId(),null);

    }
}
