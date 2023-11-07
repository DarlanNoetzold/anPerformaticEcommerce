package tech.noetzold.anPerformaticEcommerce.service.catalog;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.catalog.MediaModel;
import tech.noetzold.anPerformaticEcommerce.repository.catalog.MediaModelRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("media")
public class MediaModelService {

    @Autowired
    MediaModelRepository mediaModelRepository;

    @Transactional
    public List<MediaModel> findAllMediaModel(){
        return mediaModelRepository.findAll();
    }

    @Transactional
    public MediaModel findMediaModelById(UUID id){
        return mediaModelRepository.findById(id).orElse(null);
    }

    @Transactional
    public MediaModel saveMediaModel(MediaModel mediaModel){
        return mediaModelRepository.save(mediaModel);
    }

    @Transactional
    public void deleteMediaModel(UUID id){
        mediaModelRepository.deleteById(id);
    }
}
