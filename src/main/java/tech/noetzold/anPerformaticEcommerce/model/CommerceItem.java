package tech.noetzold.anPerformaticEcommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.noetzold.anPerformaticEcommerce.model.catalog.SkuModel;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommerceItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID commerceItemId;

    @OneToOne
    private SkuModel skuModel;

    @Temporal(TemporalType.DATE)
    private Date enabledDate;

    private boolean enable;
}
