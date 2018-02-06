package com.erikalves.application.controllers;


import com.erikalves.application.model.Image;
import com.erikalves.application.service.ImageService;
import com.erikalves.application.utils.RestApiResponseTo;
import com.erikalves.application.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/store/api/v1/images/")
class ImageController {

    //private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;


    @GetMapping(value = "/product/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Iterable<Image>>> getInclude(@PathVariable("product_id") String productId) {
        return ResponseEntity.ok(new RestApiResponseTo<>(imageService.findAllImagesByProductId(Util.LongfyId(productId))));
    }


    //delete
    @DeleteMapping(value = "/{image_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<String>> delete(@PathVariable("image_id") String imageId) {
        imageService.delete(Util.LongfyId(imageId));
        return ResponseEntity.ok(new RestApiResponseTo<>(imageId));
    }

    // create
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Image>> create(@RequestBody Image image) {
        Image savedImage = imageService.save(image);
        return ResponseEntity.created(URI.create("/" + image.getImageId())).body(new RestApiResponseTo<>(savedImage));
    }


    // update
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody Image image) {
        imageService.update(image);
        return ResponseEntity.noContent().build();
    }



}