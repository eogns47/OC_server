package OrangeCorps.LBridge.Domain.TID;

import OrangeCorps.LBridge.Domain.TID.TIDQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TIDQuestionRepository extends JpaRepository<TIDQuestion, Long> {
}
