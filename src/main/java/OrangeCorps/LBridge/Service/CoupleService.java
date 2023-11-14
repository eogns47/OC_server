package OrangeCorps.LBridge.Service;

import OrangeCorps.LBridge.Entity.User;
import OrangeCorps.LBridge.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoupleService {
    @Autowired
    UserRepository userRepository;

    public String findCoupleOfUser(String userId){
        User user = userRepository.findByUserId(userId);
        if(user.getCoupleId() == null){

        }
        return user.getCoupleId();
    }

    public String findCountryOfCouple(String userId){
        String coupleId = findCoupleOfUser(userId);
        return userRepository.findByUserId(coupleId).getCountry();
    }
}
