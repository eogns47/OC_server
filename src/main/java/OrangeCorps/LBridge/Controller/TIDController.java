package OrangeCorps.LBridge.Controller;

import OrangeCorps.LBridge.Entity.TIDAnswer;
import OrangeCorps.LBridge.Entity.TIDQuestion;
import OrangeCorps.LBridge.Service.TIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/tid/questions")
public class TIDController {

    @Autowired
    private TIDService tidService;

    // TID 질문 불러오기
    @GetMapping
    public ResponseEntity<TIDQuestion> getQuestions() {
        List<TIDQuestion> questions = tidService.getAllQuestions();

        if (questions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Random random = new Random();
        int randomIndex = random.nextInt(questions.size());


        TIDQuestion questionToShow = questions.get(randomIndex);
        return new ResponseEntity<>(questionToShow, HttpStatus.OK);
    }

    // TID 답안제출
    @PostMapping("/{questionId}")
    public ResponseEntity<String> createAnswer(
            @RequestParam Long answerId,
            @PathVariable Long questionId,
            @RequestBody Map<String, String> answerData,
            @RequestHeader(name = "Authorization") String token,
            @RequestParam String userId,
            @RequestParam String coupleId) {
        tidService.createAnswer(answerId, questionId, answerData.get("answer"), token, userId, coupleId);
        return new ResponseEntity<>("POST TID answer to question success", HttpStatus.CREATED);
    }

    // TID 답안 확인
    @GetMapping("/{questionId}")
    public ResponseEntity<List<TIDAnswer>> getAnswer(@RequestParam String combinedKey) {
        List<TIDAnswer> answer = tidService.getAnswer(combinedKey);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}
