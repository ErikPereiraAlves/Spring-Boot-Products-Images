package com.erikalves.application.controllers;


import com.erikalves.application.model.Product;
import com.erikalves.application.service.ProductService;
import com.erikalves.application.utils.RestApiResponseTo;
import com.erikalves.application.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/store/api/1/products")
class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;



    @GetMapping(value = "/exclude", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Iterable<Product>>> findAllExclude() {
        Iterable<Product> products = productService.findAll();
        for(Product product : products){
            product.setProducts(null);
        }

        return ResponseEntity.ok(new RestApiResponseTo<>(products));
    }

    @GetMapping(value = "/include", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Iterable<Product>>> findAllInclude() {
        Iterable<Product> products = productService.findAll();
        return ResponseEntity.ok(new RestApiResponseTo<>(products));
    }


    @GetMapping(value = "/exclude/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Product>>  getExclude(@PathVariable("product_id") String productId) {
        LOGGER.debug("[ProductController] product id "+productId);
        Product product = productService.get(Util.LongfyId(productId));
        LOGGER.debug("[ProductController] found following product excluding its relationships {} ",product.toString());
        return ResponseEntity.ok(new RestApiResponseTo<>(product));
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