package tech.noetzold.anPerformaticEcommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(url= "http://localhost:6000" , name = "shipping")
public class ShippingClient {
}
