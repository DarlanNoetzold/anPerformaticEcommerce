package tech.noetzold.anPerformaticEcommerce.model.payment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.wildfly.common.annotation.NotNull;
import tech.noetzold.anPerformaticEcommerce.model.CustomerModel;
import tech.noetzold.anPerformaticEcommerce.model.payment.enums.PaymentMethod;
import tech.noetzold.anPerformaticEcommerce.model.payment.enums.PaymentState;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.BoletoModel;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.CardModel;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.PaypalModel;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.PixModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PaymentModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentId;

    @NotNull
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private PaymentState paymentState;

    @NotNull
    private boolean hasErrors;

    @NotNull
    private double totalAmount;

    @NotNull
    private String orderId;

    private Date registerDate;

    private double dicountAmount;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "boleto_model_id")
    private BoletoModel boletoModel;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "card_model_id")
    private CardModel cardModel;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "pix_model_id")
    private PixModel pixModel;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "paypal_model_id")
    private PaypalModel paypalModel;

    @OneToMany(mappedBy = "payment")
    @Fetch(FetchMode.JOIN)
    private List<InvoiceModel> invoices;


}
