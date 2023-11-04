package tech.noetzold.anPerformaticEcommerce.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.catalog.MediaModel;
import tech.noetzold.anPerformaticEcommerce.repository.catalog.MediaModelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class MediaModelService {

    @Autowired
    MediaModelRepository mediaModelRepository;

    public List<MediaModel> findAllMediaModel(){
        return mediaModelRepository.findAll();
    }

    public MediaModel findMediaModelById(UUID id){
        return mediaModelRepository.findById(id).orElse(null);
    }

    public MediaModel saveMediaModel(MediaModel mediaModel){
        return mediaModelRepository.save(mediaModel);
    }

    public void deleteMediaModel(UUID id){
        mediaModelRepository.deleteById(id);
    }
}
