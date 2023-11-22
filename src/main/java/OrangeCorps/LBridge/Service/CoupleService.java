package OrangeCorps.LBridge.Service;

import OrangeCorps.LBridge.Domain.User.User;
import OrangeCorps.LBridge.Domain.User.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static OrangeCorps.LBridge.Config.Config.*;

@Service
public class CoupleService {
    @Autowired
    UserRepository userRepository;

    public String findCoupleOfUser(String userId){
        Optional<User> user = userRepository.findByUserId(userId);

        if(user.isPresent()){
            return user.get().getCoupleId();
        }
        else{
            throw new IllegalArgumentException(NOT_FOUND_USER);
        }
    }

    public String findCountryOfCouple(String userId){
        String coupleId = findCoupleOfUser(userId);
        User coupleUser =userRepository.findByUserId(coupleId).get();
        return coupleUser.getCountry();
    }
}
