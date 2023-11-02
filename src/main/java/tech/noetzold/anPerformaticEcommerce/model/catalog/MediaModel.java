package tech.noetzold.anPerformaticEcommerce.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wildfly.common.annotation.NotNull;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MediaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID mediaId;

    private String thumbnailImageURL;

    private String smallImageUrl;

    private String mediumImageUrl;

    private String largeImageUrl;

    private String zoomImageUrl;

    private Boolean integrated;

    private Date integratedDate;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skuId")
    private SkuModel sku;
}
