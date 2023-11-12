package OrangeCorps.LBridge.Service;

import OrangeCorps.LBridge.Entity.User;
import OrangeCorps.LBridge.Repository.UserRepository;
import OrangeCorps.LBridge.Validator.UserValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static OrangeCorps.LBridge.Config.*;

@Service
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
        User userById = userRepository.findById(userId).orElse(null);
        User userByCoupleId = userRepository.findByUserId(coupleId);

        if (userById != null) {
            users.add(userById);
        }

        if (userByCoupleId != null) {
            users.add(userByCoupleId);
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
