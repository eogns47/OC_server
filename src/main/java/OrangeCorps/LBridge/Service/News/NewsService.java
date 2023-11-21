package OrangeCorps.LBridge.Service.News;

import static OrangeCorps.LBridge.Config.Config.My_API_KEY_IS;
import static OrangeCorps.LBridge.Config.Config.QUERY_IS;

import OrangeCorps.LBridge.Domain.News.NewsDTO;
import OrangeCorps.LBridge.Service.CoupleService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static OrangeCorps.LBridge.Config.Config.*;

@Service
public class NewsService {

    @Autowired
    CoupleService coupleService;

    @Value("${news.api.url}")
    private String apiUrl;

    @Value("${news.api.key}")
    private String apiKey;

    @Autowired
    RestTemplate restTemplate;

    public NewsDTO getNewsOfCouple(String userId,Integer idx) {
        NewsDTO newsDTO;
        String coupleCountry = coupleService.findCountryOfCouple(userId);
        String APIUrl = makeUrl(coupleCountry);

        GetNYTimesArticles getNYTimesArticles = new GetNYTimesArticles(restTemplate);
        getNYTimesArticles.setOptions(APIUrl,idx);
        newsDTO = getNYTimesArticles.getArticle();


        return newsDTO;
    }

    public String makeUrl(String coupleCountry){
        return apiUrl + QUERY_IS + coupleCountry + My_API_KEY_IS + apiKey;
    }

    public List<NewsDTO> getTenOfNewsOfCouple(String userId){
        List<NewsDTO> newsDTOs=new ArrayList<>();
        for(int idx=NEWS_START_INDEX; idx<NEWS_END_INDEX; idx++){
            newsDTOs.add(getNewsOfCouple(userId,idx));
        }
        return newsDTOs;
    }

}
