package tech.noetzold.anPerformaticEcommerce.controller.shipping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.shipping.ShippingModel;
import tech.noetzold.anPerformaticEcommerce.service.RabbitmqService;
import tech.noetzold.anPerformaticEcommerce.service.shipping.ShippingModelService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@RestController
@RequestMapping("/ecommerce/v1/shipping/shipping")
public class ShippingModelController {

    @Autowired
    ShippingModelService shippingModelService;

    @Autowired
    private RabbitmqService rabbitmqService;

    private static final Logger logger = LoggerFactory.getLogger(ShippingModelController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<ShippingModel>> getAll() {
        Collection<ShippingModel> shippingModels = shippingModelService.findAllShippingModel();
        if (shippingModels.isEmpty()) {
            logger.warn("No shippingModel register");
            return new ResponseEntity<Collection<ShippingModel>>(HttpStatus.NO_CONTENT);
        }

        logger.info("shippingModels "+shippingModels.size()+" returned");
        return new ResponseEntity<Collection<ShippingModel>>(shippingModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ShippingModel> getShippingModelById(@PathVariable("id") String id) {
        ShippingModel shippingModel = shippingModelService.findShippingModelById(UUID.fromString(id));
        if (shippingModel == null) {
            logger.warn("shippingModel not found");
            return new ResponseEntity<ShippingModel>(HttpStatus.NOT_FOUND);
        }
        logger.info("shippingModel "+shippingModel.getShippingId()+" returned");
        return new ResponseEntity<ShippingModel>(shippingModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ShippingModel> update(@RequestBody ShippingModel shippingModel, @PathVariable("id") String id) {
        if (shippingModel == null) {
            logger.warn("shippingModel is null");
            return new ResponseEntity<ShippingModel>(HttpStatus.BAD_REQUEST);
        }

        shippingModel = shippingModelService.updateShippingModel(UUID.fromString(id), shippingModel);
        logger.info("Create shippingModel: " + shippingModel);
        return new ResponseEntity<ShippingModel>(shippingModel, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ShippingModel> save(@RequestBody ShippingModel shippingModel) {
        if (shippingModel == null) {
            logger.warn("shippingModel is null");
            return new ResponseEntity<ShippingModel>(HttpStatus.BAD_REQUEST);
        }

        rabbitmqService.sendMessage(RabbitmqQueues.SHIPPING_QUEUE, shippingModel);
        logger.info("Send message shippingModel: " + shippingModel);
        return new ResponseEntity<ShippingModel>(shippingModel, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            shippingModelService.deleteShippingModel(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove shippingModel", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove shippingModel: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
