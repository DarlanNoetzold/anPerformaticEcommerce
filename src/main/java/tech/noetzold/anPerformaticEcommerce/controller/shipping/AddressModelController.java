package tech.noetzold.anPerformaticEcommerce.controller.shipping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.shipping.AddressModel;
import tech.noetzold.anPerformaticEcommerce.service.RabbitmqService;
import tech.noetzold.anPerformaticEcommerce.service.shipping.AddressModelService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@RestController
@RequestMapping("/ecommerce/v1/shipping/address")
public class AddressModelController {

    @Autowired
    AddressModelService addressModelService;

    @Autowired
    private RabbitmqService rabbitmqService;

    private static final Logger logger = LoggerFactory.getLogger(AddressModelController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<AddressModel>> getAll() {
        Collection<AddressModel> addressModels = addressModelService.findAllAddressModel();
        if (addressModels.isEmpty()) {
            logger.warn("No addressModel register");
            return new ResponseEntity<Collection<AddressModel>>(HttpStatus.NO_CONTENT);
        }

        logger.info("addressModels "+addressModels.size()+" returned");
        return new ResponseEntity<Collection<AddressModel>>(addressModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AddressModel> getAddressModelById(@PathVariable("id") String id) {
        AddressModel addressModel = null;
        try {
            addressModel = addressModelService.findAddressModelById(UUID.fromString(id));
        } catch (Exception exception){
            logger.error("Error to get addressModel");
            return new ResponseEntity<AddressModel>(HttpStatus.BAD_REQUEST);
        }
        logger.info("addressModel "+addressModel.getAddressId()+" returned");
        return new ResponseEntity<AddressModel>(addressModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AddressModel> update(@RequestBody AddressModel addressModel, @PathVariable("id") String id) {
        if (addressModel == null) {
            logger.warn("addressModel is null");
            return new ResponseEntity<AddressModel>(HttpStatus.BAD_REQUEST);
        }

        addressModel = addressModelService.updateAddressModel(UUID.fromString(id), addressModel);
        logger.info("Create addressModel: " + addressModel);
        return new ResponseEntity<AddressModel>(addressModel, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AddressModel> save(@RequestBody AddressModel addressModel) {
        if (addressModel == null) {
            logger.warn("addressModel is null");
            return new ResponseEntity<AddressModel>(HttpStatus.BAD_REQUEST);
        }

        rabbitmqService.sendMessage(RabbitmqQueues.ADDRESS_QUEUE, addressModel);
        logger.info("Send message addressModel: " + addressModel);
        return new ResponseEntity<AddressModel>(addressModel, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            addressModelService.deleteAddressModel(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove addressModel", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove addressModel: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
