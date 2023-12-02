package tech.noetzold.anPerformaticEcommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import tech.noetzold.anPerformaticEcommerce.model.payment.PaymentModel;

@Service
@FeignClient(url= "http://localhost:5000/api/catalog/v1/attribute" , name = "catalog")
public interface CatalogClient {

    @GetMapping("/sendUpdateNotification")
    void getPayment(@RequestHeader(value = "Authorization", required = true) String authorizationHeader);
}
