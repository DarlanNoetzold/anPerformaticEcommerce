package tech.noetzold.anPerformaticEcommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.model.Order;
import tech.noetzold.anPerformaticEcommerce.service.OrderService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
@RestController
@RequestMapping("/ecommerce/v1/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Order>> getAll() {
        Collection<Order> orders = orderService.findAllOrder();
        if (orders.isEmpty()) {
            logger.warn("No order register");
            return new ResponseEntity<Collection<Order>>(HttpStatus.NO_CONTENT);
        }

        logger.info("orders "+orders.size()+" returned");
        return new ResponseEntity<Collection<Order>>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrderById(@PathVariable("id") String id) {
        Order order = orderService.findOrderById(UUID.fromString(id));
        if (order == null) {
            logger.warn("order not found");
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        logger.info("order "+order.getOrderId()+" returned");
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> save(@RequestBody Order order) {
        if (order == null) {
            logger.warn("order is null");
            return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
        }

        order = orderService.saveOrder(order);
        logger.info("Create order: " + order);
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            orderService.deleteOrder(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove order", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove order: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
