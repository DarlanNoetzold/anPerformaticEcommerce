package tech.noetzold.anPerformaticEcommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.noetzold.anPerformaticEcommerce.model.payment.PaymentModel;
import tech.noetzold.anPerformaticEcommerce.model.shipping.ShippingModel;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;

    @ManyToOne
    private CustomerModel customerModel;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PaymentModel> paymentModels;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ShippingModel> shippingModels;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CommerceItem> commerceItems;

    @Temporal(TemporalType.DATE)
    private Date initDate;

    @Temporal(TemporalType.DATE)
    private Date lastUpdate;

    private OrderState orderState;


}
