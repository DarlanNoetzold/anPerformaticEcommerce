package tech.noetzold.anPerformaticEcommerce.controller.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.model.catalog.ProductModel;
import tech.noetzold.anPerformaticEcommerce.service.catalog.ProductModelService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ecommerce/v1/catalog/product")
public class ProductModelController {

    @Autowired
    ProductModelService productModelService;

    private static final Logger logger = LoggerFactory.getLogger(ProductModelController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<ProductModel>> getAll() {
        Collection<ProductModel> productModels = productModelService.findAllProductModel();
        if (productModels.isEmpty()) {
            logger.warn("No productModel register");
            return new ResponseEntity<Collection<ProductModel>>(HttpStatus.NO_CONTENT);
        }

        logger.info("productModels "+productModels.size()+" returned");
        return new ResponseEntity<Collection<ProductModel>>(productModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductModel> getProductModelById(@PathVariable("id") String id) {
        ProductModel productModel = productModelService.findProductModelById(UUID.fromString(id));
        if (productModel == null) {
            logger.warn("productModel not found");
            return new ResponseEntity<ProductModel>(HttpStatus.NOT_FOUND);
        }
        logger.info("productModel "+productModel.getProductId()+" returned");
        return new ResponseEntity<ProductModel>(productModel, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProductModel> save(@RequestBody ProductModel productModel) {
        if (productModel == null) {
            logger.warn("productModel is null");
            return new ResponseEntity<ProductModel>(HttpStatus.BAD_REQUEST);
        }

        productModel = productModelService.saveProductModel(productModel);
        logger.info("Create productModel: " + productModel);
        return new ResponseEntity<ProductModel>(productModel, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            productModelService.deleteProductModel(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove productModel", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove productModel: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
