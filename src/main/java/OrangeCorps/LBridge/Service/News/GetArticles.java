package OrangeCorps.LBridge.Service.News;

import OrangeCorps.LBridge.DTO.NewsApiResponse;
import OrangeCorps.LBridge.DTO.NewsDTO;

public interface GetArticles {

    void setNewsAPIResponse(String APIUrl);

    NewsDTO getArticle();
    String getArticleUrl();
    String getArticleSummary();
    String getArticleHeadLine();
    String getArticlePubDate();

}
