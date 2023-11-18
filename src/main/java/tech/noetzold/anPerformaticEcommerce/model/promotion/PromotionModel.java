package tech.noetzold.anPerformaticEcommerce.model.promotion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.noetzold.anPerformaticEcommerce.model.promotion.enums.PromotionType;
import tech.noetzold.anPerformaticEcommerce.model.promotion.enums.ValueType;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PromotionModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID promoId;
    private String promoName;
    private String promoDescription;
    private ValueType valueType;
    private Double value;
    private PromotionType promotionType;
    private String promoCompDesc;
    private Double promoCompType;
    private Double promoDtlId;
    private String rules;
    private Date startDate;
    private Date endDate;
    private String applyToCode;
    private Double discountLimit;
    private String exceptionParentId;

}
