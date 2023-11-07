package tech.noetzold.anPerformaticEcommerce.controller.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.model.catalog.CategoryModel;
import tech.noetzold.anPerformaticEcommerce.service.catalog.CategoryModelService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ecommerce/v1/catalog/category")
public class CategoryModelController {

    @Autowired
    CategoryModelService categoryModelService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryModelController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<CategoryModel>> getAll() {
        Collection<CategoryModel> categoryModels = categoryModelService.findAllCategoryModel();
        if (categoryModels.isEmpty()) {
            logger.warn("No categoryModel register");
            return new ResponseEntity<Collection<CategoryModel>>(HttpStatus.NO_CONTENT);
        }

        logger.info("categoryModels "+categoryModels.size()+" returned");
        return new ResponseEntity<Collection<CategoryModel>>(categoryModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryModel> getCategoryModelById(@PathVariable("id") String id) {
        CategoryModel categoryModel = categoryModelService.findCategoryModelById(UUID.fromString(id));
        if (categoryModel == null) {
            logger.warn("categoryModel not found");
            return new ResponseEntity<CategoryModel>(HttpStatus.NOT_FOUND);
        }
        logger.info("categoryModel "+categoryModel.getCategoryId()+" returned");
        return new ResponseEntity<CategoryModel>(categoryModel, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CategoryModel> save(@RequestBody CategoryModel categoryModel) {
        if (categoryModel == null) {
            logger.warn("categoryModel is null");
            return new ResponseEntity<CategoryModel>(HttpStatus.BAD_REQUEST);
        }

        categoryModel = categoryModelService.saveCategoryModel(categoryModel);
        logger.info("Create categoryModel: " + categoryModel);
        return new ResponseEntity<CategoryModel>(categoryModel, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            categoryModelService.deleteCategoryModel(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove categoryModel", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove categoryModel: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
