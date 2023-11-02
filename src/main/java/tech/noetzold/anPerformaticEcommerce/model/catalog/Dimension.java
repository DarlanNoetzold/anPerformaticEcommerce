package tech.noetzold.anPerformaticEcommerce.model.catalog;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class Dimension {

    private BigDecimal width;
    private String lwhUom;
    private BigDecimal height;
    private BigDecimal length;
    private BigDecimal weight;
    private String weightUom;
}
