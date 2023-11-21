package OrangeCorps.LBridge.Service;

import OrangeCorps.LBridge.Domain.User.User;
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

import static OrangeCorps.LBridge.Config.Config.*;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserValidator userValidator;

    public ResponseEntity<String> linkCouple(String userId, String coupleId) {

        List<User> users = findBothUser(userId, coupleId);
        boolean isBothExisted = userValidator.validateUserListLength(users,PAIR_OF_COUPLE);

        if(!isBothExisted)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_COUPLE_USER);

        registCouple(userId,coupleId);
        return ResponseEntity.ok(COUPLE_LINK_SUCCESS);
    }

    public List<User> findBothUser(String userId, String coupleId) {
        List<User> users = new ArrayList<>();
        Optional<User> userById = userRepository.findById(userId);
        Optional<User> userByCoupleId = userRepository.findByUserId(coupleId);

        if(userById.isPresent()){
            users.add(userById.get());
        }
        else{
            log.error("존재하지 않는 유저입니다.");
        }

        if(userByCoupleId.isPresent()){
            users.add(userByCoupleId.get());
        }
        else{
            log.error("존재하지 않는 유저입니다.");
        }

        return users;
    }

    public void registCouple(String userId,String coupleId){
        Optional<User> optionalUser = userRepository.findById(userId);
        User existingUser = optionalUser.get();
        existingUser.updateCoupleId(coupleId);

        userRepository.save(existingUser);
    }
}
