package tech.noetzold.anPerformaticEcommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ShopCart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID shopCartId;

    @OneToOne
    private OrderModel order;


}
