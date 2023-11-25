package OrangeCorps.LBridge.Domain.News;

import OrangeCorps.LBridge.Domain.TID.TIDQuestion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, String> {
    List<News> findTop5ByCountryOrderByPublishedDateDesc(String country);
}
