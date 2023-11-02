package tech.noetzold.anPerformaticEcommerce.model.catalog;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wildfly.common.annotation.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SkuModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID skuId;

    private String displayName;

    private String partnerId;

    private Long sellerId;

    private String ean;

    private BigDecimal price;

    private BigDecimal salePrice;

    private Long stockLevel;

    private Boolean enabled;

    private Boolean integrated;

    private Date integratedDate;

    @Embedded
    private Dimension packageDimensionModel;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductModel product;

    @OneToMany(mappedBy = "sku", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MediaModel> medias;

    @OneToMany(mappedBy = "sku", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttributeModel> attributes;

    @OneToMany(mappedBy = "sku", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KeyWordModel> keywords;
}
