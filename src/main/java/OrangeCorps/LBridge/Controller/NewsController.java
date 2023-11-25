package OrangeCorps.LBridge.Controller;


import OrangeCorps.LBridge.Domain.News.News;
import OrangeCorps.LBridge.Domain.News.NewsDTO;
import OrangeCorps.LBridge.Service.News.NewsService;
import OrangeCorps.LBridge.Service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    @GetMapping("/news")
    public ResponseEntity<List<News>> getLatestNews(@RequestParam String userId) {
        List<News> latestNews = newsService.getLatestNewsByCountry(userId);
        return new ResponseEntity<>(latestNews, HttpStatus.OK);
    }

}
