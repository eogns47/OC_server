package OrangeCorps.LBridge.Service;

import OrangeCorps.LBridge.Entity.TIDAnswer;
import OrangeCorps.LBridge.Entity.TIDQuestion;

import java.util.List;


public interface TidService {
    List<TIDQuestion> getAllQuestions();
    void createAnswer(Long questionId, String answer, String token);
    TIDAnswer getAnswer(Long answerId);
}
