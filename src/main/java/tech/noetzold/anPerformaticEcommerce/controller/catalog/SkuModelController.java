package tech.noetzold.anPerformaticEcommerce.controller.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.noetzold.anPerformaticEcommerce.service.catalog.SkuModelService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ecommerce/v1/catalog/sku")
public class SkuModelController {

    @Autowired
    SkuModelService skuModelService;
}
