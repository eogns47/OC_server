package OrangeCorps.LBridge.Domain.News;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class NewsDTO {
    private String url;
    private String headLine;
    private String summary;
    private String publishedDate;
    private String imgUrl;

    @Override
    public String toString() {
        return "\nNewsDTO\n{" +
                "\ntitle='" + headLine + '\'' +
                "\n, summary='" + summary + '\'' +
                "\n, publishedDate'" + publishedDate + '\''+
                "\n, imgUrl'"+imgUrl+'\''+
                "\n}";
    }
}
