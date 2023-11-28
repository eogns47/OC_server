package OrangeCorps.LBridge.Service;

import OrangeCorps.LBridge.Domain.User.User;
import OrangeCorps.LBridge.Domain.User.UserDTO;
import OrangeCorps.LBridge.Domain.User.UserRepository;
import OrangeCorps.LBridge.Service.Validator.UserValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static OrangeCorps.LBridge.Config.Config.*;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserValidator userValidator;


    public String findCountry(String uuid){
        Optional<User> optionalUser = userRepository.findByUuid(uuid);
        if(optionalUser.isEmpty()){
            throw new IllegalArgumentException(NOT_FOUND_USER);
        }
        return optionalUser.get().getCountry();
    }

    public String saveUser(UserDTO userDTO){
        User user = convertToUserEntity(userDTO);
        userRepository.save(user);

        return user.getUuid();
    }
    private User convertToUserEntity(UserDTO userDTO) {
        return new User(userDTO);
    }

    public String getCoupleIdByUuid(String userId){
        Optional<String> coupleIdOptional = userRepository.findCoupleIdByUuid(userId);

        return coupleIdOptional.orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_COUPLE_USER));
    }

    public boolean coupleExist(String userId){
        Optional<String> coupleIdOptional = userRepository.findCoupleIdByUuid(userId);

        return coupleIdOptional.isPresent();
    }
}
