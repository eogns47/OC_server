package OrangeCorps.LBridge.Service.News;

import OrangeCorps.LBridge.Domain.News.NewsDTO;

public interface GetArticles {

    void setNewsAPIResponse(String APIUrl);

    NewsDTO getArticle();
    String getArticleUrl();
    String getArticleSummary();
    String getArticleHeadLine();
    String getArticlePubDate();

}
