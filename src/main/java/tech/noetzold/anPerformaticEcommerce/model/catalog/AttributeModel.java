package tech.noetzold.anPerformaticEcommerce.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AttributeModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID attributeId;

    private String name;

    private String description;

    private String type;

    private String imageUrl;

    private String hexCode;

    private String internalName;

    private String priority;

    private Boolean integrated;

    private Date integratedDate;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skuId")
    private SkuModel sku;

}
