package tech.noetzold.anPerformaticEcommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(url= "http://localhost:5000" , name = "catalog")
public class CatalogClient {
}
