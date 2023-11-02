package tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PixModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID pixId;
    private String chavePix;
    private String descricao;
    private String identificadorTransacao;
}
