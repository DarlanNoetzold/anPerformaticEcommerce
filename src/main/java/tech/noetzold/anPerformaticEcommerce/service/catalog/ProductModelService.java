package tech.noetzold.anPerformaticEcommerce.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.catalog.ProductModel;
import tech.noetzold.anPerformaticEcommerce.repository.catalog.ProductModelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProductModelService {

    @Autowired
    ProductModelRepository productModelRepository;

    public List<ProductModel> findAllProductModel(){
        return productModelRepository.findAll();
    }

    public ProductModel findProductModelById(UUID id){
        return productModelRepository.findById(id).orElse(null);
    }

    public ProductModel saveProductModel(ProductModel productModel){
        return productModelRepository.save(productModel);
    }

    public void deleteProductModel(UUID id){
        productModelRepository.deleteById(id);
    }
}
