package com.erikalves.application.controllers;


import com.erikalves.application.model.Image;
import com.erikalves.application.model.Product;
import com.erikalves.application.service.ImageService;
import com.erikalves.application.service.ProductService;
import com.erikalves.application.utils.RestApiResponseTo;
import com.erikalves.application.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/store/api/v1/images")
class ImageController {

    //private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductService productService;


    @GetMapping(value = "/product/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Iterable<Image>>> getProductImages(@PathVariable("product_id") String productId) {

        Iterable <Image> images =imageService.findAllImagesByProductId(Util.LongfyId(productId));

        if(null!=images && images.spliterator().getExactSizeIfKnown() >0) {
            return ResponseEntity.ok(new RestApiResponseTo<>(images));
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/{image_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Image>> getImage(@PathVariable("image_id") String imageId) {

        Image image =imageService.get(Util.LongfyId(imageId));

        if(null!=image) {
            return ResponseEntity.ok(new RestApiResponseTo<>(image));
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    //delete
    @DeleteMapping(value = "/{image_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<String>> delete(@PathVariable("image_id") String imageId) {

        imageService.delete(Util.LongfyId(imageId));

        return ResponseEntity.ok(new RestApiResponseTo<>(imageId));
    }

    // create
    @PostMapping(value = "/{product_id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Image>> create(@PathVariable("product_id") String productId, @RequestBody Image image) {

        Product product = productService.get(Util.LongfyId(productId));
        image.setProduct(product);
        Image savedImage = imageService.save(image);

        return ResponseEntity.created(URI.create("/" + image.getImageId())).body(new RestApiResponseTo<>(savedImage));
    }


    // update
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Image>> update(@RequestBody Image image) {

        Image foundImage = imageService.get(image.getImageId());
        foundImage.setUrl(image.getUrl());
        imageService.update(foundImage);

        return ResponseEntity.created(URI.create("/" + foundImage.getImageId())).body(new RestApiResponseTo<>(foundImage));
    }



}