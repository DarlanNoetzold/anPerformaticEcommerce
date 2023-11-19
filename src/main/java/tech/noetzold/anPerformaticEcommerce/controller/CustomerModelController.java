package tech.noetzold.anPerformaticEcommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.CustomerModel;
import tech.noetzold.anPerformaticEcommerce.model.CustomerModel;
import tech.noetzold.anPerformaticEcommerce.service.CustomerModelService;
import tech.noetzold.anPerformaticEcommerce.service.RabbitmqService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
@RestController
@RequestMapping("/ecommerce/v1/customer")
public class CustomerModelController {

    @Autowired
    CustomerModelService customerModelService;

    @Autowired
    private RabbitmqService rabbitmqService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerModelController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<CustomerModel>> getAll() {
        Collection<CustomerModel> customerModels = customerModelService.findAllCustomerModel();
        if (customerModels.isEmpty()) {
            logger.warn("No customerModel register");
            return new ResponseEntity<Collection<CustomerModel>>(HttpStatus.NO_CONTENT);
        }

        logger.info("customerModels "+customerModels.size()+" returned");
        return new ResponseEntity<Collection<CustomerModel>>(customerModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CustomerModel> getCustomerModelById(@PathVariable("id") String id) {
        CustomerModel customerModel = null;
        try {
            customerModel = customerModelService.findCustomerModelById(UUID.fromString(id));
        } catch (Exception exception){
            logger.error("Error to get customerModel");
            return new ResponseEntity<CustomerModel>(HttpStatus.BAD_REQUEST);
        }
        logger.info("customerModel "+customerModel.getCustomerId()+" returned");
        return new ResponseEntity<CustomerModel>(customerModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CustomerModel> update(@RequestBody CustomerModel customerModel, @PathVariable("id") String id) {
        if (customerModel == null) {
            logger.warn("customerModel is null");
            return new ResponseEntity<CustomerModel>(HttpStatus.BAD_REQUEST);
        }

        customerModel = customerModelService.updateCustomerModel(UUID.fromString(id), customerModel);
        logger.info("Create customerModel: " + customerModel);
        return new ResponseEntity<CustomerModel>(customerModel, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CustomerModel> save(@RequestBody CustomerModel customerModel) {
        if (customerModel == null) {
            logger.warn("customerModel is null");
            return new ResponseEntity<CustomerModel>(HttpStatus.BAD_REQUEST);
        }

        rabbitmqService.sendMessage(RabbitmqQueues.CUSTOMER_QUEUE, customerModel);
        logger.info("Send message customerModel: " + customerModel);
        return new ResponseEntity<CustomerModel>(customerModel, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            customerModelService.deleteCustomerModel(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove customerModel", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove customerModel: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
