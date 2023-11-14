package OrangeCorps.LBridge.Service.News;

import OrangeCorps.LBridge.DTO.NewsApiResponse;
import OrangeCorps.LBridge.DTO.NewsDTO;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetNYTimesArticles implements GetArticles {
    private NewsApiResponse newsApiResponse;

    private Integer idx;
    @Autowired
    private RestTemplate restTemplate;

    public GetNYTimesArticles(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setOptions(String APIUrl , Integer idx){
        setNewsAPIResponse(APIUrl);
        setIdx(idx);
    }

    public void setNewsAPIResponse(String APIUrl){
        this.newsApiResponse = restTemplate.getForObject(APIUrl, NewsApiResponse.class);
    }

    private void setIdx(Integer idx){
        this.idx=idx;
    }



    @Override
    public NewsDTO getArticle() {
        return NewsDTO.builder()
                .url(getArticleUrl())
                .headLine(getArticleHeadLine())
                .published_date(getArticlePubDate())
                .summary(getArticleSummary())
                .build();
    }

    @Override
    public String getArticleUrl() {
        return newsApiResponse.getResponse().getDocs().get(idx).getUrl();
    }


    @Override
    public String getArticleHeadLine() {
        return newsApiResponse.getResponse().getDocs().get(idx).getHeadline().getMain();
    }

    @Override
    public String getArticlePubDate() {
        return newsApiResponse.getResponse().getDocs().get(idx).getPubDate();
    }

    @Override
    public String getArticleSummary() {
        return newsApiResponse.getResponse().getDocs().get(idx).getSummary();
    }
}
