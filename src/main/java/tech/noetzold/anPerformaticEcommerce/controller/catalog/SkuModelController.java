package tech.noetzold.anPerformaticEcommerce.controller.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.catalog.SkuModel;
import tech.noetzold.anPerformaticEcommerce.service.RabbitmqService;
import tech.noetzold.anPerformaticEcommerce.service.catalog.SkuModelService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@RestController
@RequestMapping("/ecommerce/v1/catalog/sku")
public class SkuModelController {

    @Autowired
    SkuModelService skuModelService;

    @Autowired
    private RabbitmqService rabbitmqService;

    private static final Logger logger = LoggerFactory.getLogger(SkuModelController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<SkuModel>> getAll() {
        Collection<SkuModel> skuModels = skuModelService.findAllSkuModel();
        if (skuModels.isEmpty()) {
            logger.warn("No skuModel register");
            return new ResponseEntity<Collection<SkuModel>>(HttpStatus.NO_CONTENT);
        }

        logger.info("skuModels "+skuModels.size()+" returned");
        return new ResponseEntity<Collection<SkuModel>>(skuModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SkuModel> getSkuModelById(@PathVariable("id") String id) {
        SkuModel skuModel = null;
        try {
            skuModel = skuModelService.findSkuModelById(UUID.fromString(id));
        } catch (Exception exception){
            logger.error("Error to get skuModel");
            return new ResponseEntity<SkuModel>(HttpStatus.BAD_REQUEST);
        }

        logger.info("skuModel "+skuModel.getSkuId()+" returned");
        return new ResponseEntity<SkuModel>(skuModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SkuModel> update(@RequestBody SkuModel skuModel, @PathVariable("id") String id) {
        if (skuModel == null) {
            logger.warn("skuModel is null");
            return new ResponseEntity<SkuModel>(HttpStatus.BAD_REQUEST);
        }

        skuModel = skuModelService.updateSkuModel(UUID.fromString(id), skuModel);
        logger.info("Create skuModel: " + skuModel);
        return new ResponseEntity<SkuModel>(skuModel, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SkuModel> save(@RequestBody SkuModel skuModel) {
        if (skuModel == null) {
            logger.warn("skuModel is null");
            return new ResponseEntity<SkuModel>(HttpStatus.BAD_REQUEST);
        }

        rabbitmqService.sendMessage(RabbitmqQueues.SKU_QUEUE, skuModel);
        logger.info("Send message skuModel: " + skuModel);
        return new ResponseEntity<SkuModel>(skuModel, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            skuModelService.deleteSkuModel(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove skuModel", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove skuModel: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
