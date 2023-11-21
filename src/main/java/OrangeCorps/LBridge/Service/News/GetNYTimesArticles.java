package OrangeCorps.LBridge.Service.News;

import OrangeCorps.LBridge.Domain.News.NewsApiResponseDTO;
import OrangeCorps.LBridge.Domain.News.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetNYTimesArticles implements GetArticles {
    private NewsApiResponseDTO newsApiResponseDTO;

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
        this.newsApiResponseDTO = restTemplate.getForObject(APIUrl, NewsApiResponseDTO.class);
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
        return newsApiResponseDTO.getResponse().getDocs().get(idx).getUrl();
    }


    @Override
    public String getArticleHeadLine() {
        return newsApiResponseDTO.getResponse().getDocs().get(idx).getHeadline().getMain();
    }

    @Override
    public String getArticlePubDate() {
        return newsApiResponseDTO.getResponse().getDocs().get(idx).getPubDate();
    }

    @Override
    public String getArticleSummary() {
        return newsApiResponseDTO.getResponse().getDocs().get(idx).getSummary();
    }
}
