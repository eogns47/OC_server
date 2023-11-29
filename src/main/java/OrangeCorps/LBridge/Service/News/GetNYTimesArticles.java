package OrangeCorps.LBridge.Service.News;

import OrangeCorps.LBridge.Domain.News.NewsApiResponseDTO;
import OrangeCorps.LBridge.Domain.News.NewsApiResponseDTO.NewsApiResponseBody.Docs.Multimedia;
import OrangeCorps.LBridge.Domain.News.NewsDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static OrangeCorps.LBridge.Config.Config.*;

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
                .publishedDate(getArticlePubDate())
                .summary(getArticleSummary())
                .imgUrl(getArticleImgUrl())
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

    @Override
    public String getArticleImgUrl() {
        List<Multimedia> multimediaList = newsApiResponseDTO.getResponse().getDocs().get(idx).getMultimediaList();

        if (multimediaList != null && !multimediaList.isEmpty()) {
            return NEWS_IMAGE_BASE_URL + multimediaList.get(0).getImgUrl();
        } else {
            return null;
        }

    }
}
