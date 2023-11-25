package OrangeCorps.LBridge.Domain.News;

import OrangeCorps.LBridge.Domain.TID.TIDQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, String> {
}
