package com.erikalves.application.service;

import com.erikalves.application.model.Product;
import com.erikalves.application.repositories.ProductRepository;
import com.erikalves.application.utils.Util;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImplTest.class);

    @Autowired
    @Qualifier(value="ProductService")
    ProductService productService;

   @Autowired (required=true)
    ProductRepository productRepository;

    Product product;

    Product savedProduct;

    @Before
    public void  begin(){

        product = new Product();
        product.setProductParentId(1l);
        product.setProductName("Smartphone Product service impl test");
        product.setProductDesc("ProductServiceImplTest");
        product.setProductPrice(100.55);
        product.setProductCreatedTs(Util.getCurrentDate());
        product.setProductUpdatedTs(Util.getCurrentDate());

        savedProduct = productService.save(product);
        LOGGER.debug("saved product ID {}",savedProduct.getProductId());
        Assert.assertNotNull(savedProduct.getProductId());

    }

    @Test
    public void getId() {

        Long productId = productService.getId(savedProduct);
        Assert.assertNotNull(productId);
    }

    @Test
    public void getRepository() {

        CrudRepository<Product, Long> crudRepository = productService.getRepository();
        Assert.assertNotNull(crudRepository);
        Assert.assertTrue(EqualsBuilder.reflectionEquals(productRepository,crudRepository));
    }

    @Test
    public void findAllChildrenProductsByProductParentId() {

        List<Product> list = productService.findProductIncludingRelationships(savedProduct.getProductParentId());
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() >0);

    }
}