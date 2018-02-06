package com.erikalves.application.controllers;


import com.erikalves.application.model.Product;
import com.erikalves.application.service.ProductServiceImpl;
import com.erikalves.application.utils.RestApiResponseTo;
import com.erikalves.application.utils.Util;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/store/api/v1/products")
class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductServiceImpl productService;


    @GetMapping(value = "/exclude", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Iterable<Object>>> findAllExclude() {
        return ResponseEntity.ok(new RestApiResponseTo<>(productService.findAllExcludingRelationships()));
    }

    @GetMapping(value = "/include", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Iterable<Product>>> findAllInclude() {
        return ResponseEntity.ok(new RestApiResponseTo<>(productService.findAll()));
    }


    @GetMapping(value = "/exclude/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>  getExclude(@PathVariable("product_id") String productId) {
        Object product =productService.findProductExcludingRelationships(Util.LongfyId(productId));
        String json = new Gson().toJson(product);
        return ResponseEntity.ok(json);
    }

    @GetMapping(value = "/include/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Iterable<Product>>> getInclude(@PathVariable("product_id") String productId) {
        return ResponseEntity.ok(new RestApiResponseTo<>(productService.findProductIncludingRelationships(Util.LongfyId(productId))));
    }



    //delete
    @DeleteMapping(value = "/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<String>> delete(@PathVariable("product_id") String productId) {
        productService.delete(Util.LongfyId(productId));
        return ResponseEntity.ok(new RestApiResponseTo<>(productId));
    }

    // create
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Product>> create(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return ResponseEntity.created(URI.create("/" + product.getProductId())).body(new RestApiResponseTo<>(savedProduct));
    }


    // update
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody Product product) {
        productService.update(product);
        return ResponseEntity.noContent().build();
    }



}