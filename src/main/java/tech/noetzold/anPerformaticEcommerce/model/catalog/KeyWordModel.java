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
public class KeyWordModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID keyWordId;

    private String keyWord;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skuId")
    private SkuModel sku;

    private Boolean integrated;

    private Date integratedDate;
}
