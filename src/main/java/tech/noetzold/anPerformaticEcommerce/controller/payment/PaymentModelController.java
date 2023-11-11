package tech.noetzold.anPerformaticEcommerce.controller.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.payment.PaymentModel;
import tech.noetzold.anPerformaticEcommerce.service.RabbitmqService;
import tech.noetzold.anPerformaticEcommerce.service.payment.PaymentModelService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@RestController
@RequestMapping("/ecommerce/v1/payment/payment")
public class PaymentModelController {

    @Autowired
    PaymentModelService paymentModelService;

    @Autowired
    private RabbitmqService rabbitmqService;

    private static final Logger logger = LoggerFactory.getLogger(PaymentModelController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<PaymentModel>> getAll() {
        Collection<PaymentModel> paymentModels = paymentModelService.findAllPaymentModel();
        if (paymentModels.isEmpty()) {
            logger.warn("No paymentModel register");
            return new ResponseEntity<Collection<PaymentModel>>(HttpStatus.NO_CONTENT);
        }

        logger.info("paymentModels "+paymentModels.size()+" returned");
        return new ResponseEntity<Collection<PaymentModel>>(paymentModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PaymentModel> getPaymentModelById(@PathVariable("id") String id) {
        PaymentModel paymentModel = paymentModelService.findPaymentModelById(UUID.fromString(id));
        if (paymentModel == null) {
            logger.warn("paymentModel not found");
            return new ResponseEntity<PaymentModel>(HttpStatus.NOT_FOUND);
        }
        logger.info("paymentModel "+paymentModel.getPaymentId()+" returned");
        return new ResponseEntity<PaymentModel>(paymentModel, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PaymentModel> save(@RequestBody PaymentModel paymentModel) {
        if (paymentModel == null) {
            logger.warn("paymentModel is null");
            return new ResponseEntity<PaymentModel>(HttpStatus.BAD_REQUEST);
        }

        rabbitmqService.sendMessage(RabbitmqQueues.PAYMENT_QUEUE, paymentModel);
        logger.info("Send message paymentModel: " + paymentModel);
        return new ResponseEntity<PaymentModel>(paymentModel, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            paymentModelService.deletePaymentModel(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove paymentModel", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove paymentModel: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
