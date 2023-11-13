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
public class TIDImplementation implements TidService {
    @Autowired
    private TIDQuestionRepository tidQuestionRepository;

    @Autowired
    private TIDAnswerRepository tidAnswerRepository;

    public List<TIDQuestion> getAllQuestions() {
        return tidQuestionRepository.findAll();
    }

    public void createAnswer(Long questionId, String answer, String token) {
        // token validation logic 넣기

        TIDAnswer newAnswer = new TIDAnswer();

        newAnswer.setQuestionId(questionId);
        newAnswer.setAnswer(answer);
        newAnswer.setTimestamp(LocalDateTime.now());
        tidAnswerRepository.save(newAnswer);
    }

    public TIDAnswer getAnswer(Long answerId) {
        return tidAnswerRepository.findById(answerId).orElse(null);
    }
}

