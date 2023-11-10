package tech.noetzold.anPerformaticEcommerce.controller.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.model.catalog.AttributeModel;
import tech.noetzold.anPerformaticEcommerce.service.catalog.AttributeModelService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@RestController
@RequestMapping("/ecommerce/v1/catalog/attribute")
public class AttributeModelController {

    @Autowired
    AttributeModelService attributeModelService;

    private static final Logger logger = LoggerFactory.getLogger(AttributeModelController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<AttributeModel>> getAll() {
        Collection<AttributeModel> attributeModels = attributeModelService.findAllAttributeModel();
        if (attributeModels.isEmpty()) {
            logger.warn("No attributeModel register");
            return new ResponseEntity<Collection<AttributeModel>>(HttpStatus.NO_CONTENT);
        }

        logger.info("attributeModels "+attributeModels.size()+" returned");
        return new ResponseEntity<Collection<AttributeModel>>(attributeModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AttributeModel> getAttributeModelById(@PathVariable("id") String id) {
        AttributeModel attributeModel = attributeModelService.findAttributeModelById(UUID.fromString(id));
        if (attributeModel == null) {
            logger.warn("attributeModel not found");
            return new ResponseEntity<AttributeModel>(HttpStatus.NOT_FOUND);
        }
        logger.info("attributeModel "+attributeModel.getAttributeId()+" returned");
        return new ResponseEntity<AttributeModel>(attributeModel, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AttributeModel> save(@RequestBody AttributeModel attributeModel) {
        if (attributeModel == null) {
            logger.warn("attributeModel is null");
            return new ResponseEntity<AttributeModel>(HttpStatus.BAD_REQUEST);
        }

        attributeModel = attributeModelService.saveAttributeModel(attributeModel);
        logger.info("Create attributeModel: " + attributeModel);
        return new ResponseEntity<AttributeModel>(attributeModel, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            attributeModelService.deleteAttributeModel(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove attributeModel", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove attributeModel: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
