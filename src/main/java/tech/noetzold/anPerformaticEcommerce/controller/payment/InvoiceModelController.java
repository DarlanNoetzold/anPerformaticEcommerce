package tech.noetzold.anPerformaticEcommerce.controller.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.catalog.SkuModel;
import tech.noetzold.anPerformaticEcommerce.model.payment.InvoiceModel;
import tech.noetzold.anPerformaticEcommerce.service.RabbitmqService;
import tech.noetzold.anPerformaticEcommerce.service.payment.InvoiceModelService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@RestController
@RequestMapping("/ecommerce/v1/payment/invoice")
public class InvoiceModelController {

    @Autowired
    InvoiceModelService invoiceModelService;

    @Autowired
    private RabbitmqService rabbitmqService;

    private static final Logger logger = LoggerFactory.getLogger(InvoiceModelController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<InvoiceModel>> getAll() {
        Collection<InvoiceModel> invoiceModels = invoiceModelService.findAllInvoiceModel();
        if (invoiceModels.isEmpty()) {
            logger.warn("No invoiceModel register");
            return new ResponseEntity<Collection<InvoiceModel>>(HttpStatus.NO_CONTENT);
        }

        logger.info("invoiceModels "+invoiceModels.size()+" returned");
        return new ResponseEntity<Collection<InvoiceModel>>(invoiceModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<InvoiceModel> getInvoiceModelById(@PathVariable("id") String id) {
        InvoiceModel invoiceModel = null;
        try {
            invoiceModel = invoiceModelService.findInvoiceModelById(UUID.fromString(id));
        } catch (Exception exception){
            logger.error("Error to get invoiceModel");
            return new ResponseEntity<InvoiceModel>(HttpStatus.BAD_REQUEST);
        }
        logger.info("invoiceModel "+invoiceModel.getInvoiceId()+" returned");
        return new ResponseEntity<InvoiceModel>(invoiceModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<InvoiceModel> update(@RequestBody InvoiceModel invoiceModel, @PathVariable("id") String id) {
        if (invoiceModel == null) {
            logger.warn("invoiceModel is null");
            return new ResponseEntity<InvoiceModel>(HttpStatus.BAD_REQUEST);
        }

        invoiceModel = invoiceModelService.updateInvoiceModel(UUID.fromString(id), invoiceModel);
        logger.info("Create invoiceModel: " + invoiceModel);
        return new ResponseEntity<InvoiceModel>(invoiceModel, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<InvoiceModel> save(@RequestBody InvoiceModel invoiceModel) {
        if (invoiceModel == null) {
            logger.warn("invoiceModel is null");
            return new ResponseEntity<InvoiceModel>(HttpStatus.BAD_REQUEST);
        }

        rabbitmqService.sendMessage(RabbitmqQueues.INVOICE_QUEUE, invoiceModel);
        logger.info("Send message invoiceModel: " + invoiceModel);
        return new ResponseEntity<InvoiceModel>(invoiceModel, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            invoiceModelService.deleteInvoiceModel(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove invoiceModel", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove invoiceModel: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
