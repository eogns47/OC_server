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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static OrangeCorps.LBridge.Config.Config.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> userRegist(@RequestBody UserDTO userDTO) {
        String userID = userService.saveUser(userDTO);
        return ResponseEntity.ok("USER " + userID + " INSERT SUCCESSFULLY");

    }

    @GetMapping("/myCouple")
    public ResponseEntity<User> getUserCountry(@RequestParam String uuid){
        User couple= userService.myCouple(uuid);
        return ResponseEntity.ok(couple);
    }



}
