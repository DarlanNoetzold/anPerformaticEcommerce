package tech.noetzold.anPerformaticEcommerce.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.wildfly.common.annotation.NotNull;
import tech.noetzold.anPerformaticEcommerce.model.payment.paymentMethods.CardModel;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CustomerModel {
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
