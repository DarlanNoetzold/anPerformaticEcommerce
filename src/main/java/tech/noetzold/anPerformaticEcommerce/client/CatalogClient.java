package tech.noetzold.anPerformaticEcommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(url= "http://localhost:5000/api/catalog/v1/attribute" , name = "catalog")
public interface CatalogClient {
}
