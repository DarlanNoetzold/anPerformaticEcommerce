package tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.noetzold.anPerformaticEcommerce.model.payment.enums.CardType;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CardModel implements Serializable {

    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cardId;
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;
    private CardType cardType;
}
