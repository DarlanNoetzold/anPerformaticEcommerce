package tech.noetzold.anPerformaticEcommerce.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.repository.catalog.CategoryModelRepository;

@Service
public class CategoryModelService {

    @Autowired
    CategoryModelRepository categoryModelRepository;


}
