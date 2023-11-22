package tech.noetzold.anPerformaticEcommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.model.shipping.ShippingModel;

import java.util.List;

@Service
@FeignClient(url= "http://localhost:6000/api/shipping/v1/address" , name = "shipping")
public interface ShippingClient {

    @GetMapping("/{id}")
    ShippingModel getShipping(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestParam String id);

    @GetMapping("/order/{orderId}")
    List<ShippingModel> getAllByOrderIdShipping(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestParam String orderId);

    @PostMapping()
    ShippingModel saveShipping(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestBody ShippingModel shippingModel);

    @PutMapping("/{id}")
    ShippingModel updateShipping(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestBody ShippingModel shippingModel,  @RequestParam String id);

    @DeleteMapping("/{id}")
    ShippingModel deleteShipping(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestParam String id);
}
