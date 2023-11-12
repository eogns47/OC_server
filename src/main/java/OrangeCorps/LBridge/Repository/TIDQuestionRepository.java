package OrangeCorps.LBridge.Repository;

import OrangeCorps.LBridge.Entity.TIDQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TIDQuestionRepository extends JpaRepository<TIDQuestion, Long> {
    // 질문 시간순으로 모두 출력
    List<TIDQuestion> findAllByOrderByTimeStampDesc();
}
