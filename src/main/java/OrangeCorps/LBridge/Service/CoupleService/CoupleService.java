package OrangeCorps.LBridge.Service.CoupleService;

import OrangeCorps.LBridge.Domain.User.User;
import OrangeCorps.LBridge.Domain.User.UserRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static OrangeCorps.LBridge.Config.Config.*;

@Service
public class CoupleService {
    @Autowired
    UserRepository userRepository;

    public String findCoupleOfUser(String userId) {
        Optional<User> user = userRepository.findByUuid(userId);

        return userRepository.findByUuid(userId)
                .map(User::getCoupleId)
                .orElseThrow(() -> new NoSuchElementException(NOT_FOUND_COUPLE_USER));

    }

    public String findCountryOfCouple(String userId) {

        String coupleId = findCoupleOfUser(userId);
        User coupleUser = userRepository.findByUuid(coupleId).get();
        System.out.println(coupleUser.getCountry());
        return coupleUser.getCountry();
    }
}
