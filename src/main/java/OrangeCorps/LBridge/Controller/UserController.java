package OrangeCorps.LBridge.Controller;

import OrangeCorps.LBridge.Domain.User.UserDTO;
import OrangeCorps.LBridge.Domain.User.User;
import OrangeCorps.LBridge.Domain.User.UserRepository;
import OrangeCorps.LBridge.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static OrangeCorps.LBridge.Config.Config.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public ResponseEntity<String> userRegist(@RequestBody UserDTO userDTO) {
        User user = convertToUserEntity(userDTO);
        userRepository.save(user);
        return ResponseEntity.ok(String.format(USER_SAVE_SUCCESS, user.getName()));

    }

    @PostMapping("/user/couple")
    public ResponseEntity<String> linkUserToCouple(@RequestParam String userId, @RequestParam String coupleId) {
        return userService.linkCouple(userId, coupleId);
    }

    private User convertToUserEntity(UserDTO userDTO) {
        return new User(userDTO);
    }

}
