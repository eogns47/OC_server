package OrangeCorps.LBridge.Controller;

import OrangeCorps.LBridge.Service.News.NewsService;
import OrangeCorps.LBridge.Service.News.ScheduledApiCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/news/caller")
public class NewsCallerController {


    @Autowired
    ScheduledApiCaller scheduledApiCaller;

    @GetMapping("/start")
    public ResponseEntity<String> startCrawl(){
        scheduledApiCaller.contextInitialized();
        return ResponseEntity.ok("CRAWL START");
    }

    @GetMapping("/end")
    public ResponseEntity<String> endCrawl(){
        scheduledApiCaller.contextDestroyed();
        return ResponseEntity.ok("CRAWL END");
    }
}
