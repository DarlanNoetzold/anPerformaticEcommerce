package tech.noetzold.anPerformaticEcommerce.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.model.payment.PaymentModel;

import java.util.List;

@Service
@FeignClient(url= "http://localhost:7000/api/payment/v1/payment" , name = "payment")
public interface PaymentClient {

    @GetMapping("/{id}")
    PaymentModel getPayment(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestParam String id);

    @GetMapping("/order/{orderId}")
    List<PaymentModel> getAllByOrderIdPayment(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,@RequestParam String orderId);

    @GetMapping("/order/{userId}")
    List<PaymentModel> getAllByUserIdPayment(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,@RequestParam String userId);

    @PostMapping()
    PaymentModel savePayment(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestBody PaymentModel paymentModel);

    @PutMapping("/{id}")
    PaymentModel updatePayment(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestBody PaymentModel paymentModel,  @RequestParam String id);

    @DeleteMapping("/{id}")
    PaymentModel deletePayment(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestParam String id);
}
