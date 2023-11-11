package OrangeCorps.LBridge.Repository;

import OrangeCorps.LBridge.Entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

    //값이 없을 경우 null 반환
    User findByUserId(String userId);
}

