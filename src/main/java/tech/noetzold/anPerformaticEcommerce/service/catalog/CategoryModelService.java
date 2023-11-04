package tech.noetzold.anPerformaticEcommerce.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.catalog.CategoryModel;
import tech.noetzold.anPerformaticEcommerce.repository.catalog.CategoryModelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryModelService {

    @Autowired
    CategoryModelRepository categoryModelRepository;

    public List<CategoryModel> findAllCategoryModel(){
        return categoryModelRepository.findAll();
    }

    public CategoryModel findCategoryModelById(UUID id){
        return categoryModelRepository.findById(id).orElse(null);
    }

    public CategoryModel saveCategoryModel(CategoryModel categoryModel){
        return categoryModelRepository.save(categoryModel);
    }

    public void deleteCategoryModel(UUID id){
        categoryModelRepository.deleteById(id);
    }
}
