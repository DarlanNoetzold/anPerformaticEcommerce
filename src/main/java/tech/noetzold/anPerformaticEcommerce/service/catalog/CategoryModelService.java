package tech.noetzold.anPerformaticEcommerce.service.catalog;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.catalog.CategoryModel;
import tech.noetzold.anPerformaticEcommerce.repository.catalog.CategoryModelRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("category")
public class CategoryModelService {

    @Autowired
    CategoryModelRepository categoryModelRepository;

    @Transactional
    public List<CategoryModel> findAllCategoryModel(){
        return categoryModelRepository.findAll();
    }

    @Transactional
    public CategoryModel findCategoryModelById(UUID id){
        return categoryModelRepository.findById(id).orElse(null);
    }

    @Transactional
    public CategoryModel updateCategoryModel(UUID id, CategoryModel categoryModel){
        categoryModel.setCategoryId(id);
        return categoryModelRepository.save(categoryModel);
    }
    
    @Transactional
    public CategoryModel saveCategoryModel(CategoryModel categoryModel){
        return categoryModelRepository.save(categoryModel);
    }

    @Transactional
    public void deleteCategoryModel(UUID id){
        categoryModelRepository.deleteById(id);
    }
}
