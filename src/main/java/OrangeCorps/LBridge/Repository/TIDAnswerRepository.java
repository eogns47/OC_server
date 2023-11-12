package OrangeCorps.LBridge.Repository;

import OrangeCorps.LBridge.Entity.TIDAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TIDAnswerRepository extends JpaRepository<TIDAnswer, Long> {
    // 응답 questionId로 검색
    List<TIDAnswer> findByQuestionId(Long questionId);
}
