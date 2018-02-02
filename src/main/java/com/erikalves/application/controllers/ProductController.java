package com.erikalves.application.controllers;


import com.erikalves.application.model.Product;
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
import java.util.List;


@RestController
@RequestMapping("/store/api/1/products")
class ProductController {

    //private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;



    @GetMapping(value = "/exclude", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Iterable<Product>>> findAllExclude() {
        return ResponseEntity.ok(new Response<>(productService.findAll()));
    }

    @GetMapping(value = "/include", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Iterable<Product>>> findAllInclude() {
        return ResponseEntity.ok(new Response<>(productService.findAll()));
    }




    @GetMapping(value = "/exclude/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Product>> getExclude(@PathVariable("product_id") String productId) {
        return ResponseEntity.ok(new Response<>(productService.get(Util.LongfyId(productId))));
    }

    @GetMapping(value = "/include/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Product>> getInclude(@PathVariable("product_id") String productId) {
        return ResponseEntity.ok(new Response<>(productService.get(Util.LongfyId(productId))));
    }



    //delete
    @DeleteMapping(value = "/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<String>> delete(@PathVariable("product_id") String productId) {
        productService.delete(Util.LongfyId(productId));
        return ResponseEntity.ok(new Response<>(productId));
    }

    // create
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Product>> create(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return ResponseEntity.created(URI.create("/" + product.getProductId())).body(new Response<>(savedProduct));
    }


    // update
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody Product product) {
        productService.update(product);
        return ResponseEntity.noContent().build();
    }



}