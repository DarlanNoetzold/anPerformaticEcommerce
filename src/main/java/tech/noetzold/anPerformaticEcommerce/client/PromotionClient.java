package tech.noetzold.anPerformaticEcommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.model.promotion.PromotionModel;

import java.util.List;

@Service
@FeignClient(url= "http://localhost:4000/api/promo/v1/promotion" , name = "promotion")
public interface PromotionClient {

    @GetMapping("/{id}")
    PromotionModel getPromotion(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestParam String id);

    @GetMapping()
    List<PromotionModel> getAllPromotion(@RequestHeader(value = "Authorization", required = true) String authorizationHeader);

    @PostMapping()
    PromotionModel savePromotion(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestBody PromotionModel shippingModel);

    @PutMapping("/{id}")
    PromotionModel updatePromotion(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestBody PromotionModel shippingModel,  @RequestParam String id);

    @DeleteMapping("/{id}")
    PromotionModel deletePromotion(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestParam String id);
}
