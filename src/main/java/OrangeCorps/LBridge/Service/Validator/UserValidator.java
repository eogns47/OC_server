package OrangeCorps.LBridge.Service.Validator;

import static OrangeCorps.LBridge.Config.Config.NOT_FOUND_COUPLE_USER;
import static OrangeCorps.LBridge.Config.Config.NOT_FOUND_USER;

import OrangeCorps.LBridge.Domain.User.User;
import OrangeCorps.LBridge.Domain.User.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    @Autowired
    UserRepository userRepository;
    public void validateUserListLength(List<User> users,int required){
        if(users.size()!= required){
            throw new NullPointerException(NOT_FOUND_USER);
        }
    }

    public void validBothUser(String userId, String coupleId) {
        Optional<User> userById = userRepository.findById(userId);
        Optional<User> userByCoupleId = userRepository.findById(coupleId);

        if(userById.isEmpty())
            throw new IllegalArgumentException(NOT_FOUND_USER);


        if(userByCoupleId.isEmpty()){
            throw new IllegalArgumentException(NOT_FOUND_COUPLE_USER);
        }

        return;
    }
}
