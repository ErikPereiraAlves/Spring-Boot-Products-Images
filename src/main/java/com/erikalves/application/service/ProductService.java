package com.erikalves.application.service;


import com.erikalves.application.model.Product;

import java.util.List;


public interface ProductService extends GenericService <Product,Long>{

    List<Product> findAllProductsIncludingRelationships();

    List<Product> findProductIncludingRelationships(Long id);

    List<Product> findAllExcludingRelationships();

    Product findProductExcludingRelationships(Long id);


}
