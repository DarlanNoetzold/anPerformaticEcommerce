package tech.noetzold.anPerformaticEcommerce.controller.shipping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.model.shipping.AddressModel;
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
        AddressModel addressModel = addressModelService.findAddressModelById(UUID.fromString(id));
        if (addressModel == null) {
            logger.warn("addressModel not found");
            return new ResponseEntity<AddressModel>(HttpStatus.NOT_FOUND);
        }
        logger.info("addressModel "+addressModel.getAddressId()+" returned");
        return new ResponseEntity<AddressModel>(addressModel, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AddressModel> save(@RequestBody AddressModel addressModel) {
        if (addressModel == null) {
            logger.warn("addressModel is null");
            return new ResponseEntity<AddressModel>(HttpStatus.BAD_REQUEST);
        }

        addressModel = addressModelService.saveAddressModel(addressModel);
        logger.info("Create addressModel: " + addressModel);
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
