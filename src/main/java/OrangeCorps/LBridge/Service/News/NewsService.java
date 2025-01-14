package OrangeCorps.LBridge.Service.News;

import static OrangeCorps.LBridge.Config.Config.My_API_KEY_IS;
import static OrangeCorps.LBridge.Config.Config.QUERY_IS;

import OrangeCorps.LBridge.Domain.News.News;
import OrangeCorps.LBridge.Domain.News.NewsDTO;
import OrangeCorps.LBridge.Domain.News.NewsRepository;
import OrangeCorps.LBridge.Domain.User.User;
import OrangeCorps.LBridge.Domain.User.UserRepository;
import OrangeCorps.LBridge.Service.CoupleService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static OrangeCorps.LBridge.Config.Config.*;

@Service
public class NewsService {

    @Autowired
    private CoupleService coupleService;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserRepository userRepository;

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

    public List<NewsDTO> getNewsesOfCouple(String country) {
        List<NewsDTO> newsDTOs = new ArrayList<>();
        for (int idx = NEWS_START_INDEX; idx < NEWS_END_INDEX; idx++) {
            newsDTOs.add(getNewsOfCouple(idx, country));
        }
        return newsDTOs;
    }

    public void saveNews() {
        List<NewsDTO> newsDTOs = new ArrayList<>();

        List<String> distinctCountries = getDistinctCountries();

        for(String country : distinctCountries) {
            newsDTOs = getNewsesOfCouple(country);

            for (NewsDTO newsDTO : newsDTOs) {

                // News 엔티티 생성 및 값 설정
                News newsEntity = News.builder()
                        .url(newsDTO.getUrl())
                        .headLine(newsDTO.getHeadLine())
                        .summary(newsDTO.getSummary())
                        .published_date(newsDTO.getPublished_date())
                        .country(country)
                        .build();

                newsRepository.save(newsEntity);
            }
        }
    }

    private List<String> getDistinctCountries() {
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .map(User::getCountry)
                .distinct()
                .collect(Collectors.toList());
    }

}
