package tech.noetzold.anPerformaticEcommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.OrderModel;
import tech.noetzold.anPerformaticEcommerce.service.OrderModelService;
import tech.noetzold.anPerformaticEcommerce.service.RabbitmqService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
@RestController
@RequestMapping("/ecommerce/v1/order")
public class OrderController {

    @Autowired
    OrderModelService orderModelService;

    @Autowired
    private RabbitmqService rabbitmqService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<OrderModel>> getAll() {
        Collection<OrderModel> orders = orderModelService.findAllOrder();
        if (orders.isEmpty()) {
            logger.warn("No order register");
            return new ResponseEntity<Collection<OrderModel>>(HttpStatus.NO_CONTENT);
        }

        logger.info("orders "+orders.size()+" returned");
        return new ResponseEntity<Collection<OrderModel>>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderModel> getOrderById(@PathVariable("id") String id) {
        OrderModel order = orderModelService.findOrderById(UUID.fromString(id));
        if (order == null) {
            logger.warn("order not found");
            return new ResponseEntity<OrderModel>(HttpStatus.NOT_FOUND);
        }
        logger.info("order "+order.getOrderId()+" returned");
        return new ResponseEntity<OrderModel>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OrderModel> update(@RequestBody OrderModel order, @PathVariable("id") String id) {
        if (order == null) {
            logger.warn("order is null");
            return new ResponseEntity<OrderModel>(HttpStatus.BAD_REQUEST);
        }

        order = orderModelService.updateOrder(UUID.fromString(id), order);
        logger.info("Create order: " + order);
        return new ResponseEntity<OrderModel>(order, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OrderModel> save(@RequestBody OrderModel order) {
        if (order == null) {
            logger.warn("order is null");
            return new ResponseEntity<OrderModel>(HttpStatus.BAD_REQUEST);
        }

        rabbitmqService.sendMessage(RabbitmqQueues.ORDER_QUEUE, order);
        logger.info("Send message order: " + order);
        return new ResponseEntity<OrderModel>(order, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            orderModelService.deleteOrder(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove order", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove order: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
