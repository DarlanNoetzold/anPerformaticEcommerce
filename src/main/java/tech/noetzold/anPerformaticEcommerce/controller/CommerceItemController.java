package tech.noetzold.anPerformaticEcommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.CommerceItem;
import tech.noetzold.anPerformaticEcommerce.service.CommerceItemService;
import tech.noetzold.anPerformaticEcommerce.service.RabbitmqService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
@RestController
@RequestMapping("/ecommerce/v1/commerceitem")
public class CommerceItemController {

    @Autowired
    CommerceItemService commerceItemService;

    @Autowired
    private RabbitmqService rabbitmqService;

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
        CommerceItem commerceItem = null;
        try {
            commerceItem = commerceItemService.findCommerceItemById(UUID.fromString(id));
        } catch (Exception exception){
            logger.error("Error to get commerceItem");
            return new ResponseEntity<CommerceItem>(HttpStatus.BAD_REQUEST);
        }
        logger.info("commerceItem "+commerceItem.getCommerceItemId()+" returned");
        return new ResponseEntity<CommerceItem>(commerceItem, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CommerceItem> update(@RequestBody CommerceItem commerceItem, @PathVariable("id") String id) {
        if (commerceItem == null) {
            logger.warn("commerceItem is null");
            return new ResponseEntity<CommerceItem>(HttpStatus.BAD_REQUEST);
        }

        commerceItem = commerceItemService.updateCommerceItem(UUID.fromString(id), commerceItem);
        logger.info("Create commerceItem: " + commerceItem);
        return new ResponseEntity<CommerceItem>(commerceItem, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CommerceItem> save(@RequestBody CommerceItem commerceItem) {
        if (commerceItem == null) {
            logger.warn("commerceItem is null");
            return new ResponseEntity<CommerceItem>(HttpStatus.BAD_REQUEST);
        }

        rabbitmqService.sendMessage(RabbitmqQueues.COMMERCE_ITEM_QUEUE, commerceItem);
        logger.info("Send message commerceItem: " + commerceItem);
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
