package tech.noetzold.anPerformaticEcommerce.controller.promotion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.model.promotion.PromotionModel;
import tech.noetzold.anPerformaticEcommerce.service.promotion.PromotionModelService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ecommerce/v1/promo/promotion")
public class PromotionModelController {

    @Autowired
    PromotionModelService promotionModelService;

    private static final Logger logger = LoggerFactory.getLogger(PromotionModelController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<PromotionModel>> getAll() {
        Collection<PromotionModel> promotionModels = promotionModelService.findAllPromotionModel();
        if (promotionModels.isEmpty()) {
            logger.warn("No promotionModel register");
            return new ResponseEntity<Collection<PromotionModel>>(HttpStatus.NO_CONTENT);
        }

        logger.info("promotionModels "+promotionModels.size()+" returned");
        return new ResponseEntity<Collection<PromotionModel>>(promotionModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PromotionModel> getPromotionModelById(@PathVariable("id") String id) {
        PromotionModel promotionModel = promotionModelService.findPromotionModelById(UUID.fromString(id));
        if (promotionModel == null) {
            logger.warn("promotionModel not found");
            return new ResponseEntity<PromotionModel>(HttpStatus.NOT_FOUND);
        }
        logger.info("promotionModel "+promotionModel.getPromoId()+" returned");
        return new ResponseEntity<PromotionModel>(promotionModel, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PromotionModel> save(@RequestBody PromotionModel promotionModel) {
        if (promotionModel == null) {
            logger.warn("promotionModel is null");
            return new ResponseEntity<PromotionModel>(HttpStatus.BAD_REQUEST);
        }

        promotionModel = promotionModelService.savePromotionModel(promotionModel);
        logger.info("Create promotionModel: " + promotionModel);
        return new ResponseEntity<PromotionModel>(promotionModel, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            promotionModelService.deletePromotionModel(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove promotionModel", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove promotionModel: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
