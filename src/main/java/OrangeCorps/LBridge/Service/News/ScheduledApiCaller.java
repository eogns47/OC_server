package OrangeCorps.LBridge.Service.News;

import jakarta.annotation.PostConstruct;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class ScheduledApiCaller {

    private ScheduledExecutorService scheduler;

    @Autowired
    NewsService newsService;

    public void contextInitialized() {
        // 서버가 시작될 때 실행될 코드
        System.out.println("뉴스 수집을 시작합니다. \n 간격: 하루");

        // ScheduledExecutorService 생성
        scheduler = Executors.newScheduledThreadPool(1);

        // 작업을 수행할 Runnable 객체 생성
        Runnable apiCallTask = () -> {
            try {
                // API 호출 메서드 호출
                callApi();
            } catch (Exception e) {
                e.printStackTrace(); // 예외 처리
            }
        };

        // 일정 주기로 작업 예약
        long initialDelay = 60; // 초기 지연 (0초)
        long period = 24*60*60; // 주기 (초 단위, 여기서는 24시간)
        scheduler.scheduleAtFixedRate(apiCallTask, initialDelay, period, TimeUnit.SECONDS);
    }

    public void contextDestroyed() {
        // 서버가 종료될 때 실행될 코드
        System.out.println("수집을 종료됩니다.");

        // 스케줄러 종료
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }

    private void callApi() {
        newsService.saveNews();
    }
}
