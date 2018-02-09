package com.erikalves.application.service;

import com.erikalves.application.model.Product;
import com.erikalves.application.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ProductService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public Long getId(Product entity) {
        return entity.getProductId();
    }

    @Override
    public CrudRepository<Product, Long> getRepository() {
        return this.productRepository;
    }


    @Override
    public List<Product> findAllProductsIncludingRelationships() {
        return productRepository.findAllProductsIncludingRelationships();
    }

    @Override
    public List<Product> findProductIncludingRelationships(Long id) {
        return productRepository.findProductIncludingRelationships(id);
    }


    @Override
    public List<Product> findAllExcludingRelationships() {
        return productRepository.findAllExcludingRelationships();
    }

    @Override
    public Product findProductExcludingRelationships(Long id) {
         return productRepository.findProductExcludingRelationships(id);
    }



}
