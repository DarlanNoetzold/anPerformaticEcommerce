package tech.noetzold.anPerformaticEcommerce.model.shipping;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.noetzold.anPerformaticEcommerce.model.shipping.enums.ShippingMethod;
import tech.noetzold.anPerformaticEcommerce.model.shipping.enums.State;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ShippingModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID shippingId;

    private String carrierCode;

    @NotNull
    private ShippingMethod shippingMethod;

    @NotNull
    private State state;

    @NotNull
    private String userId;

    @NotNull
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "address_model_id")
    private AddressModel addressModel;

    private String trackingUrl;

}
