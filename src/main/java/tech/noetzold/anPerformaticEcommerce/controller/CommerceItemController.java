package tech.noetzold.anPerformaticEcommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.model.CommerceItem;
import tech.noetzold.anPerformaticEcommerce.service.CommerceItemService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ecommerce/v1/commerceitem")
public class CommerceItemController {

    @Autowired
    CommerceItemService commerceItemService;

    private static final Logger logger = LoggerFactory.getLogger(CommerceItemController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<CommerceItem>> getAll() {
        Collection<CommerceItem> commerceItems = commerceItemService.findAllCommerceItem();
        if (commerceItems.isEmpty()) {
            logger.warn("No commerceItem register");
            return new ResponseEntity<Collection<CommerceItem>>(HttpStatus.NO_CONTENT);
        }

        logger.info("commerceItems "+commerceItems.size()+" returned");
        return new ResponseEntity<Collection<CommerceItem>>(commerceItems, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CommerceItem> getCommerceItemById(@PathVariable("id") String id) {
        CommerceItem commerceItem = commerceItemService.findCommerceItemById(UUID.fromString(id));
        if (commerceItem == null) {
            logger.warn("commerceItem not found");
            return new ResponseEntity<CommerceItem>(HttpStatus.NOT_FOUND);
        }
        logger.info("commerceItem "+commerceItem.getCommerceItemId()+" returned");
        return new ResponseEntity<CommerceItem>(commerceItem, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CommerceItem> save(@RequestBody CommerceItem commerceItem) {
        if (commerceItem == null) {
            logger.warn("commerceItem is null");
            return new ResponseEntity<CommerceItem>(HttpStatus.BAD_REQUEST);
        }

        commerceItem = commerceItemService.saveCommerceItem(commerceItem);
        logger.info("Create commerceItem: " + commerceItem);
        return new ResponseEntity<CommerceItem>(commerceItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            commerceItemService.deleteCommerceItem(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove commerceItem", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove commerceItem: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
