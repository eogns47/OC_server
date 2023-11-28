package OrangeCorps.LBridge.Domain.Couple;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoupleRequestRepository extends JpaRepository<CoupleRequest,String> {
    Optional<CoupleRequest> findByCoupleId(String coupleId);


}
