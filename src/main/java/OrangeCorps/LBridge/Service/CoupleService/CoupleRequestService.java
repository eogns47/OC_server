package OrangeCorps.LBridge.Service.CoupleService;

import OrangeCorps.LBridge.Domain.Couple.CoupleRequest;
import OrangeCorps.LBridge.Domain.Couple.CoupleRequestDTO;
import OrangeCorps.LBridge.Domain.Couple.CoupleRequestRepository;
import OrangeCorps.LBridge.Domain.User.UserRepository;
import OrangeCorps.LBridge.Service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import OrangeCorps.LBridge.Domain.User.User;
import static OrangeCorps.LBridge.Config.Config.*;
@Service
public class CoupleRequestService {

    @Autowired
    CoupleRequestRepository coupleRequestRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CoupleRegistService coupleRegistService;

    @Autowired
    UserService userService;

    public String sendCoupleRequest(CoupleRequestDTO coupleRequestDTO){
        Optional<User> coupleUser = userRepository.findByUuid(coupleRequestDTO.getCoupleId());
        if (coupleUser.isEmpty()) {
            throw new IllegalArgumentException(NOT_FOUND_COUPLE_USER + coupleRequestDTO.getCoupleId());
        }


        CoupleRequest coupleRequest = CoupleRequest.builder()
                .uuid(coupleRequestDTO.getUuid())
                .coupleId(coupleRequestDTO.getCoupleId())
                .build();

        coupleRequestRepository.save(coupleRequest);

        return checkSendRequestEachOther(coupleRequestDTO);
    }

    public String checkSendRequestEachOther(CoupleRequestDTO coupleRequestDTO){
        String userId = coupleRequestDTO.getUuid();
        String coupleId=checkCoupleRequest(userId);
        System.out.println(coupleId+","+coupleRequestDTO.getCoupleId());
        if(coupleId.equals(coupleRequestDTO.getCoupleId())){
            return coupleRegistService.registCouple(coupleRequestDTO);
        }
        else{
            return COUPLE_REGISTRATION_REQUEST_SUCCESS;
        }
    }

    public String checkCoupleRequest(String uuid){
        Optional<CoupleRequest> coupleRequestOptional = coupleRequestRepository.findByCoupleId(uuid);

        if(coupleRequestOptional.isEmpty()) return COUPLE_REQUEST_NOT_EXIST;
        else{
            return coupleRequestOptional.get().getUuid();
        }
    }

    public String declineReceiveRequest(CoupleRequestDTO coupleRequestDTO){
        CoupleRequest coupleRequest = CoupleRequest.builder()
                .uuid(coupleRequestDTO.getUuid())
                .coupleId(coupleRequestDTO.getCoupleId())
                .build();
        coupleRequestRepository.delete(coupleRequest);

        return DECLINE_SUCCESSFUL;
    }

    public String cancelCoupleRequest(String uuid){
        Optional<CoupleRequest> optionalCoupleRequest = coupleRequestRepository.findById(uuid);

        optionalCoupleRequest.ifPresentOrElse(
                coupleRequestRepository::delete,
                () -> {
                    throw new IllegalArgumentException(SEND_REQUEST_NOT_EXIST);
                }
        );
        return DELETE_SUCCESSFUL;
    }

    public String checkMyRequestResult(String uuid){
        if(validMyRequestAccepted(uuid)){
            return MY_REQUEST_ACCEPTED;
        }

        if(validMyRequestWaiting(uuid)){
            return MY_REQUEST_WAITING;
        }
        else{
            return MY_REQUEST_DECLINED;
        }

    }

    public boolean validMyRequestAccepted(String uuid){
        return userService.coupleExist(uuid);
    }

    public boolean validMyRequestWaiting(String uuid){
        return coupleRequestRepository.findById(uuid).isPresent();
    }


}
