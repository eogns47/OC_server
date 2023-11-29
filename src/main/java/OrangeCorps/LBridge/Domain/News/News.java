package OrangeCorps.LBridge.Domain.News;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class News {

    @Id
    @Column(length=256)
    private String url;
    @Column(length=256)
    private String headLine;
    @Column(length=256)
    private String summary;
    @Column(length=128)
    private String publishedDate;
    @Column(length=128)
    private String country;
    @Column(length = 512)
    private String imgUrl;


}
