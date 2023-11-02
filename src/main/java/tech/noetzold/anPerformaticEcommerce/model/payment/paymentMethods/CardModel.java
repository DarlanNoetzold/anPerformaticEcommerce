package tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.noetzold.model.enums.CardType;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cardId;
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;
    private CardType cardType;
}
