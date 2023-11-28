package OrangeCorps.LBridge.Controller;

import OrangeCorps.LBridge.Domain.Couple.CoupleRequestDTO;
import OrangeCorps.LBridge.Service.CoupleService.CoupleRegistService;
import OrangeCorps.LBridge.Service.CoupleService.CoupleRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user/couple")
public class CoupleController {

    @Autowired
    CoupleRequestService coupleRequestService;

    @Autowired
    CoupleRegistService coupleRegistService;



    @PostMapping("/request")
    public ResponseEntity<String> coupleRequest(@RequestBody CoupleRequestDTO coupleRequestDTO){
        String message = coupleRequestService.sendCoupleRequest(coupleRequestDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/request")
    public ResponseEntity<String> coupleRequestExist(@RequestParam String uuid){
        String message = coupleRequestService.checkCoupleRequest(uuid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/accept")
    public ResponseEntity<String> coupleRequestAccept(@RequestBody CoupleRequestDTO coupleRequestDTO){
        String message = coupleRegistService.linkCouple(coupleRequestDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/decline")
    public ResponseEntity<String> coupleRequestDecline(@RequestBody CoupleRequestDTO coupleRequestDTO){
        String message = coupleRequestService.declineReceiveRequest(coupleRequestDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/request")
    public ResponseEntity<String> cancelCoupleRequest(@RequestParam String uuid) {
        String message = coupleRequestService.cancelCoupleRequest(uuid);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/check")
    public ResponseEntity<String> coupleRequestResult(@RequestParam String uuid){
        String message = coupleRequestService.checkMyRequestResult(uuid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
