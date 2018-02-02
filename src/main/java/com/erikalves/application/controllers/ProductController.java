package com.erikalves.application.controllers;


import com.erikalves.application.model.Product;
import com.erikalves.application.service.ProductService;
import com.erikalves.application.utils.RestApiResponseTo;
import com.erikalves.application.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/store/api/1/products")
class ProductController {

    //private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;



    @GetMapping(value = "/exclude", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Iterable<Product>>> findAllExclude() {
        return ResponseEntity.ok(new RestApiResponseTo<>(productService.findAll()));
    }

    @GetMapping(value = "/include", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Iterable<Product>>> findAllInclude() {
        return ResponseEntity.ok(new RestApiResponseTo<>(productService.findAll()));
    }




    @GetMapping(value = "/exclude/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Product>> getExclude(@PathVariable("product_id") String productId) {
        return ResponseEntity.ok(new RestApiResponseTo<>(productService.get(Util.LongfyId(productId))));
    }

    @GetMapping(value = "/include/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestApiResponseTo<Product>> getInclude(@PathVariable("product_id") String productId) {
        return ResponseEntity.ok(new RestApiResponseTo<>(productService.get(Util.LongfyId(productId))));
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