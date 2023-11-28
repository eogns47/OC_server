package OrangeCorps.LBridge.Service.CoupleService;

import static OrangeCorps.LBridge.Config.Config.COUPLE_LINK_SUCCESS;
import static OrangeCorps.LBridge.Config.Config.COUPLE_REQUEST_NOT_EXIST;
import static OrangeCorps.LBridge.Config.Config.NOT_FOUND_COUPLE_USER;
import static OrangeCorps.LBridge.Config.Config.NOT_FOUND_USER;
import static OrangeCorps.LBridge.Config.Config.PAIR_OF_COUPLE;

import OrangeCorps.LBridge.Domain.Couple.CoupleRequest;
import OrangeCorps.LBridge.Domain.Couple.CoupleRequestDTO;
import OrangeCorps.LBridge.Domain.Couple.CoupleRequestRepository;
import OrangeCorps.LBridge.Domain.User.User;
import OrangeCorps.LBridge.Domain.User.UserRepository;
import OrangeCorps.LBridge.Service.Validator.UserValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoupleRegistService {

    @Autowired
    UserValidator userValidator;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CoupleRequestRepository coupleRequestRepository;

    public String linkCouple(CoupleRequestDTO coupleRequestDTO) {
        String userId = coupleRequestDTO.getUuid();
        String coupleId = coupleRequestDTO.getCoupleId();

        userValidator.validBothUser(userId, coupleId);

        if(!validRequestExist(coupleRequestDTO)){
            return COUPLE_REQUEST_NOT_EXIST;
        }

        registCouple(coupleRequestDTO);
        return COUPLE_LINK_SUCCESS;
    }


    public void registCouple(CoupleRequestDTO coupleRequestDTO){
        String userId = coupleRequestDTO.getUuid();
        String coupleId = coupleRequestDTO.getCoupleId();

        Optional<User> optionalUser = userRepository.findById(userId);
        User existingUser = optionalUser.get();
        existingUser.updateCoupleId(coupleId);

        Optional<User> optionalCoupleUser = userRepository.findByUuid(coupleId);
        User existingCoupleUser = optionalCoupleUser.get();
        existingCoupleUser.updateCoupleId(userId);

        userRepository.save(existingUser);
        userRepository.save(existingCoupleUser);
    }

    public boolean validRequestExist(CoupleRequestDTO coupleRequestDTO){
        Optional<CoupleRequest> optionalCoupleRequest = coupleRequestRepository.findById(coupleRequestDTO.getUuid());
        return optionalCoupleRequest.isPresent();
    }
}
