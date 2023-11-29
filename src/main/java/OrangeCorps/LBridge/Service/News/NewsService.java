package OrangeCorps.LBridge.Service.News;

import static OrangeCorps.LBridge.Config.Config.My_API_KEY_IS;
import static OrangeCorps.LBridge.Config.Config.QUERY_IS;

import OrangeCorps.LBridge.Domain.News.News;
import OrangeCorps.LBridge.Domain.News.NewsDTO;
import OrangeCorps.LBridge.Domain.News.NewsRepository;
import OrangeCorps.LBridge.Domain.User.User;
import OrangeCorps.LBridge.Domain.User.UserRepository;
import OrangeCorps.LBridge.Service.CoupleService.CoupleService;
import OrangeCorps.LBridge.Service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static OrangeCorps.LBridge.Config.Config.*;

@Service
@Slf4j
public class NewsService {

    @Autowired
    private CoupleService coupleService;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Value("${news.api.url}")
    private String apiUrl;

    @Value("${news.api.key}")
    private String apiKey;

    @Autowired
    RestTemplate restTemplate;

    public NewsDTO getNewsOfCouple(Integer idx,String country) {
        NewsDTO newsDTO;

        String APIUrl = makeUrl(country);

        GetNYTimesArticles getNYTimesArticles = new GetNYTimesArticles(restTemplate);
        getNYTimesArticles.setOptions(APIUrl, idx);
        newsDTO = getNYTimesArticles.getArticle();

        return newsDTO;
    }

    public String makeUrl(String country) {
        return apiUrl + QUERY_IS + country + My_API_KEY_IS + apiKey;
    }

    @Async
    public CompletableFuture<List<NewsDTO>> getNewsesOfCouple(String country) {
        List<NewsDTO> newsDTOs = new ArrayList<>();
        log.info("Start processing getNewsesOfCouple for country: {}", country);
        for (int idx = NEWS_START_INDEX; idx <= NEWS_END_INDEX; idx++) {
            try {
                newsDTOs.add(getNewsOfCouple(idx, country));
                Thread.sleep(13000);  // 13초 지연
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("Completed processing getNewsesOfCouple for country: {}", country);
        return CompletableFuture.completedFuture(newsDTOs);
    }

    public void saveNews() {

        List<String> distinctCountries = getDistinctCountries();

        for(String country : distinctCountries) {
            CompletableFuture<List<NewsDTO>> futureNews = getNewsesOfCouple(country);

            futureNews.thenAccept(newsDTOs -> {
                // 비동기 작업이 완료되면 실행되는 콜백 함수

                for (NewsDTO newsDTO : newsDTOs) {

                    // News 엔티티 생성 및 값 설정
                    News newsEntity = News.builder()
                            .url(newsDTO.getUrl())
                            .headLine(newsDTO.getHeadLine())
                            .summary(newsDTO.getSummary())
                            .publishedDate(newsDTO.getPublishedDate())
                            .country(country)
                            .imgUrl(newsDTO.getImgUrl())
                            .build();

                    newsRepository.save(newsEntity);
                }
            });
        }
    }

    private List<String> getDistinctCountries() {
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .map(User::getCountry)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<News> getLatestNewsByCountry(String userId) {
        String coupleId = userService.getCoupleIdByUuid(userId);
        String country= userService.findCountry(coupleId);

        return newsRepository.findTop5ByCountryOrderByPublishedDateDesc(country);
    }

}
