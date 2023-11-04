package tech.noetzold.anPerformaticEcommerce.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.repository.catalog.MediaModelRepository;

@Service
public class MediaModelService {

    @Autowired
    MediaModelRepository mediaModelRepository;
}
