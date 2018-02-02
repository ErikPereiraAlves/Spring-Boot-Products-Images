package com.erikalves.application.controllers;


import com.erikalves.application.model.Image;
import com.erikalves.application.model.Product;
import com.erikalves.application.service.ImageService;
import com.erikalves.application.service.ProductService;
import com.erikalves.application.utils.Response;
import com.erikalves.application.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;


@RestController
@RequestMapping("/store/api/1/images/")
class ImageController {

    //private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;






    //delete
    @DeleteMapping(value = "/{image_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<String>> delete(@PathVariable("image_id") String imageId) {
        imageService.delete(Util.LongfyId(imageId));
        return ResponseEntity.ok(new Response<>(imageId));
    }

    // create
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Image>> create(@RequestBody Image image) {
        Image savedImage = imageService.save(image);
        return ResponseEntity.created(URI.create("/" + image.getImageId())).body(new Response<>(savedImage));
    }


    // update
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody Image image) {
        imageService.update(image);
        return ResponseEntity.noContent().build();
    }



}