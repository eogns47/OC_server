package OrangeCorps.LBridge.Domain.News;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import lombok.Getter;

@Getter
public class NewsApiResponseDTO {

    @JsonProperty("response")
    private NewsApiResponseBody response;

    @Getter
    public static class NewsApiResponseBody {

        @JsonProperty("docs")
        private List<Docs> docs;

        @Getter
        public static class Docs {

            @JsonProperty("web_url")
            private  String url;

            @JsonProperty("headline")
            private Headline headline;

            @JsonProperty("abstract")
            private String summary;

            @JsonProperty("pub_date")
            private String pubDate;

            @Getter
            public static class Headline {

                @JsonProperty("main")
                private String main;

            }
        }
    }
}
