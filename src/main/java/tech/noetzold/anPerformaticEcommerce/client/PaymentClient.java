package tech.noetzold.anPerformaticEcommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(url= "http://localhost:7000" , name = "payment")
public class PaymentClient {
}
