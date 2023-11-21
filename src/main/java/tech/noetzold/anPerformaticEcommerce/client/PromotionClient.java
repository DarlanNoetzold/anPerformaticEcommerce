package tech.noetzold.anPerformaticEcommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(url= "http://localhost:4000" , name = "promotion")
public class PromotionClient {
}
