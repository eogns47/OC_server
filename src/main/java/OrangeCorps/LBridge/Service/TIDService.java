package OrangeCorps.LBridge.Service;

import OrangeCorps.LBridge.Entity.TIDAnswer;
import OrangeCorps.LBridge.Entity.TIDQuestion;

import java.util.List;


public interface TIDService {
    List<TIDQuestion> getAllQuestions();
    void createAnswer(Long answerId, Long questionId, String answer, String token, String userId, String coupleId);
    List<TIDAnswer> getAnswer(String combinedkey);
}
