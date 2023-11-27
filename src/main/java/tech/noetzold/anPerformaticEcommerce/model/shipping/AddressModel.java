package tech.noetzold.anPerformaticEcommerce.model.shipping;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AddressModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID addressId;

    @NotNull
    private String postaCode;

    @NotNull
    private String address1;

    private String address2;

    @NotNull
    private String city;

    @NotNull
    private String uf;

    @NotNull
    private String country;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private double finalShippingCost;

    @NotNull
    private String phoneNumber;

    @NotNull
    private Date deliveryDate;

    @NotNull
    private boolean riskArea;

    @NotNull
    private String userId;

}
