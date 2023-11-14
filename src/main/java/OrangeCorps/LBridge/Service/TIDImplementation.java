package OrangeCorps.LBridge.Service;

import OrangeCorps.LBridge.Entity.TIDAnswer;
import OrangeCorps.LBridge.Entity.TIDQuestion;
import OrangeCorps.LBridge.Repository.TIDAnswerRepository;
import OrangeCorps.LBridge.Repository.TIDQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TIDImplementation implements TIDService {
    @Autowired
    private TIDQuestionRepository tidQuestionRepository;

    @Autowired
    private TIDAnswerRepository tidAnswerRepository;

    public List<TIDQuestion> getAllQuestions() {
        return tidQuestionRepository.findAll();
    }

    public void createAnswer(Long answerId, Long questionId, String answer, String token, String userId, String coupleId) {
        // token validation logic 넣기

        TIDAnswer newAnswer = new TIDAnswer();

        newAnswer.setAnswerId(answerId);
        newAnswer.setQuestionId(questionId);
        newAnswer.setAnswer(answer);
        newAnswer.setTimestamp(LocalDateTime.now());
        newAnswer.setUserId(userId);
        newAnswer.setCoupleId(coupleId);
        newAnswer.setCombinedKey(newAnswer.getCombinedKey());
        tidAnswerRepository.save(newAnswer);
    }

    public List<TIDAnswer> getAnswer(String combinedKey) {
        return tidAnswerRepository.findByCombinedKey(combinedKey);
    }
}

