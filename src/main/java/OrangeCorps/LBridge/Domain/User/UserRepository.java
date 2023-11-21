package OrangeCorps.LBridge.Domain.User;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

    //값이 없을 경우 null 반환
    Optional<User> findByUserId(String userId);
}

