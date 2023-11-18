package tech.noetzold.anPerformaticEcommerce.model.catalog;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wildfly.common.annotation.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;

    private String title;

    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryModel category;

    private String brand;

    private String model;

    private String videoUrl;

    private String gender;

    private Integer warrantyTime;

    private String warrantyText;

    private Number height;

    private Number width;

    private Number weight;

    private Number length;

    private Number priceFactor;

    private Boolean calculatedPrice;

    private String definitionPriceScope;

    private Boolean hasVariations;

    private Boolean allowAutomaticSkuMarketplaceCreation;

    private String code;

    private String message;

    private Boolean integrated;

    private Date integratedDate;

}
