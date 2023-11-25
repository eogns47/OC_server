package OrangeCorps.LBridge;

import OrangeCorps.LBridge.Service.News.NewsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class NewsTest {
    @Autowired
    private NewsService newsService;

    @Test
    @DisplayName("API 정상 수신 테스트")
    void IsNewsAPISuccess(){
    }
}
