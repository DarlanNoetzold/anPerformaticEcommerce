package tech.noetzold.anPerformaticEcommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.CardModel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CustomerModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customerId;

    @NotNull
    private String userId;

    private Date registerDate;

    private Date bornDate;

    @OneToMany
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "card_id")
    private List<CardModel> cards;
}
