package tech.noetzold.anPerformaticEcommerce.controller.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.promotion.CouponModel;
import tech.noetzold.anPerformaticEcommerce.service.RabbitmqService;
import tech.noetzold.anPerformaticEcommerce.service.promotion.CouponModelService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@RestController
@RequestMapping("/ecommerce/v1/promo/coupon")
public class CouponModelController {

    @Autowired
    CouponModelService couponModelService;

    @Autowired
    private RabbitmqService rabbitmqService;

    private static final Logger logger = LoggerFactory.getLogger(CouponModelController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<CouponModel>> getAll() {
        Collection<CouponModel> couponModels = couponModelService.findAllCouponModel();
        if (couponModels.isEmpty()) {
            logger.warn("No couponModel register");
            return new ResponseEntity<Collection<CouponModel>>(HttpStatus.NO_CONTENT);
        }

        logger.info("couponModels "+couponModels.size()+" returned");
        return new ResponseEntity<Collection<CouponModel>>(couponModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CouponModel> getCouponModelById(@PathVariable("id") String id) {
        CouponModel couponModel = couponModelService.findCouponModelById(UUID.fromString(id));
        if (couponModel == null) {
            logger.warn("couponModel not found");
            return new ResponseEntity<CouponModel>(HttpStatus.NOT_FOUND);
        }
        logger.info("couponModel "+couponModel.getCouponId()+" returned");
        return new ResponseEntity<CouponModel>(couponModel, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CouponModel> save(@RequestBody CouponModel couponModel) {
        if (couponModel == null) {
            logger.warn("couponModel is null");
            return new ResponseEntity<CouponModel>(HttpStatus.BAD_REQUEST);
        }

        rabbitmqService.sendMessage(RabbitmqQueues.COUPON_QUEUE, couponModel);
        logger.info("Send message couponModel: " + couponModel);
        return new ResponseEntity<CouponModel>(couponModel, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            couponModelService.deleteCouponModel(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove couponModel", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove couponModel: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
