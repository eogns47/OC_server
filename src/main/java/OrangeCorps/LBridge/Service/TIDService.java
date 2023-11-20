package OrangeCorps.LBridge.Service;

import OrangeCorps.LBridge.Domain.TID.TIDAnswer;
import OrangeCorps.LBridge.Domain.TID.TIDQuestion;

import java.util.List;


public interface TIDService {
    List<TIDQuestion> getAllQuestions();
    void createAnswer(Long answerId, Long questionId, String answer, String token, String userId, String coupleId);
    List<TIDAnswer> getAnswer(String combinedkey);
}
