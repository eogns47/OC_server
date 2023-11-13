package OrangeCorps.LBridge.Repository;

import OrangeCorps.LBridge.Entity.TIDQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TIDQuestionRepository extends JpaRepository<TIDQuestion, Long> {
}
