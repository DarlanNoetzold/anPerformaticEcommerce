package tech.noetzold.anPerformaticEcommerce.controller.catalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.anPerformaticEcommerce.message.config.RabbitmqQueues;
import tech.noetzold.anPerformaticEcommerce.model.catalog.MediaModel;
import tech.noetzold.anPerformaticEcommerce.service.RabbitmqService;
import tech.noetzold.anPerformaticEcommerce.service.catalog.MediaModelService;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@RestController
@RequestMapping("/ecommerce/v1/catalog/media")
public class MediaModelController {

    @Autowired
    MediaModelService mediaModelService;

    @Autowired
    private RabbitmqService rabbitmqService;

    private static final Logger logger = LoggerFactory.getLogger(MediaModelController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<MediaModel>> getAll() {
        Collection<MediaModel> mediaModels = mediaModelService.findAllMediaModel();
        if (mediaModels.isEmpty()) {
            logger.warn("No mediaModel register");
            return new ResponseEntity<Collection<MediaModel>>(HttpStatus.NO_CONTENT);
        }

        logger.info("mediaModels "+mediaModels.size()+" returned");
        return new ResponseEntity<Collection<MediaModel>>(mediaModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MediaModel> getMediaModelById(@PathVariable("id") String id) {
        MediaModel mediaModel = null;
        try {
            mediaModel = mediaModelService.findMediaModelById(UUID.fromString(id));
        }catch (Exception exception){
            logger.error("Error to get mediaModel");
            return new ResponseEntity<MediaModel>(HttpStatus.BAD_REQUEST);
        }
        if (mediaModel == null) {
            logger.warn("mediaModel not found");
            return new ResponseEntity<MediaModel>(HttpStatus.NOT_FOUND);
        }
        logger.info("mediaModel "+mediaModel.getMediaId()+" returned");
        return new ResponseEntity<MediaModel>(mediaModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<MediaModel> update(@RequestBody MediaModel mediaModel, @PathVariable("id") String id) {
        if (mediaModel == null) {
            logger.warn("mediaModel is null");
            return new ResponseEntity<MediaModel>(HttpStatus.BAD_REQUEST);
        }

        mediaModel = mediaModelService.updateMediaModel(UUID.fromString(id), mediaModel);
        logger.info("Create mediaModel: " + mediaModel);
        return new ResponseEntity<MediaModel>(mediaModel, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MediaModel> save(@RequestBody MediaModel mediaModel) {
        if (mediaModel == null) {
            logger.warn("mediaModel is null");
            return new ResponseEntity<MediaModel>(HttpStatus.BAD_REQUEST);
        }

        rabbitmqService.sendMessage(RabbitmqQueues.MEDIA_QUEUE,mediaModel);
        logger.info("Send message mediaModel: " + mediaModel);
        return new ResponseEntity<MediaModel>(mediaModel, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable("id") String id) {
        try {
            mediaModelService.deleteMediaModel(UUID.fromString(id));
        }catch (Exception ex){
            logger.error("Error to remove mediaModel", ex);
            return new ResponseEntity<String>(id, HttpStatus.BAD_GATEWAY);
        }

        logger.info("Remove mediaModel: " + id);
        return new ResponseEntity<String>(id, HttpStatus.OK);
    }
}
