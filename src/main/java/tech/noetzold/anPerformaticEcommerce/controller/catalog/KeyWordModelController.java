package tech.noetzold.anPerformaticEcommerce.controller.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.catalog.KeyWordModel;
import tech.noetzold.anPerformaticEcommerce.service.RabbitmqService;
import tech.noetzold.anPerformaticEcommerce.service.catalog.KeyWordModelService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@RestController
@RequestMapping("/ecommerce/v1/catalog/keyword")
public class KeyWordModelController {

    @Autowired
    KeyWordModelService keyWordModelService;

    @Autowired
    private RabbitmqService rabbitmqService;

    private static final Logger logger = LoggerFactory.getLogger(KeyWordModelController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<KeyWordModel>> getAll() {
        Collection<KeyWordModel> keyWordModels = keyWordModelService.findAllKeyWordModel();
        if (keyWordModels.isEmpty()) {
            logger.warn("No keyWordModel register");
            return new ResponseEntity<Collection<KeyWordModel>>(HttpStatus.NO_CONTENT);
        }

        logger.info("keyWordModels "+keyWordModels.size()+" returned");
        return new ResponseEntity<Collection<KeyWordModel>>(keyWordModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<KeyWordModel> getKeyWordModelById(@PathVariable("id") String id) {
        KeyWordModel keyWordModel = keyWordModelService.findKeyWordModelById(UUID.fromString(id));
        if (keyWordModel == null) {
            logger.warn("keyWordModel not found");
            return new ResponseEntity<KeyWordModel>(HttpStatus.NOT_FOUND);
        }
        logger.info("keyWordModel "+keyWordModel.getKeyWordId()+" returned");
        return new ResponseEntity<KeyWordModel>(keyWordModel, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<KeyWordModel> save(@RequestBody KeyWordModel keyWordModel) {
        if (keyWordModel == null) {
            logger.warn("keyWordModel is null");
            return new ResponseEntity<KeyWordModel>(HttpStatus.BAD_REQUEST);
        }

        rabbitmqService.sendMessage(RabbitmqQueues.KEY_WORD_QUEUE, keyWordModel);
        logger.info("Send message keyWordModel: " + keyWordModel);
        return new ResponseEntity<KeyWordModel>(keyWordModel, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            keyWordModelService.deleteKeyWordModel(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove keyWordModel", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove keyWordModel: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
