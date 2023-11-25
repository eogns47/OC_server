package OrangeCorps.LBridge.Controller;


import OrangeCorps.LBridge.Domain.News.NewsDTO;
import OrangeCorps.LBridge.Service.News.NewsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("news")
    public ResponseEntity<Object> crawlNews(@RequestParam String userId) {

            return ResponseEntity.ok("Success");
    }

}
