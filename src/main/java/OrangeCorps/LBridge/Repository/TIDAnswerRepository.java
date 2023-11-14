package OrangeCorps.LBridge.Repository;

import OrangeCorps.LBridge.Entity.TIDAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TIDAnswerRepository extends JpaRepository<TIDAnswer, Long> {
    // 응답 datestamp + userid1 + userid2로 combined key로 조회.
    List<TIDAnswer> findByCombinedKey(String combinedKey);
}
