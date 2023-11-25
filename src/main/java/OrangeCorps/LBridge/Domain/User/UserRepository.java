package OrangeCorps.LBridge.Domain.User;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

    //값이 없을 경우 null 반환
    Optional<User> findByUuid(String uuid);

    @Query("SELECT u.coupleId FROM User u WHERE u.uuid = :uuid")
    Optional<String> findCoupleIdByUuid(@Param("uuid") String uuid);
}

