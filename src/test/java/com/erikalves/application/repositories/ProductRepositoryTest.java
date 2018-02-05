package com.erikalves.application.repositories;

import com.erikalves.application.model.Image;
import com.erikalves.application.model.Product;
import com.erikalves.application.utils.Util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryTest.class);

    @Autowired (required=true)
    ProductRepository productRepository;

    Product createdProduct;

    Set<Image> images = new HashSet<>();

    @Before
    public void  begin(){

        createdProduct = new Product();
        createdProduct.setProductParentId(1l);
        createdProduct.setProductName("Smartphone JUNIT");
        createdProduct.setProductDesc("Junit now manufactures smartphones");
        createdProduct.setProductPrice(200.00);
        createdProduct.setProductCreatedTs(Util.getCurrentTs());
        createdProduct.setProductUpdatedTs(Util.getCurrentTs());

    }


    @Test
    public void shouldCreateUpdateDeleteProduct(){

        shouldCreateProduct();
        shouldUpdateProduct();
        shouldUpdateProductWithImages();
        shouldDeleteProduct();
    }

    public void shouldCreateProduct(){

       Product savedProduct = productRepository.save(createdProduct);
       LOGGER.debug("saved product ID {}",savedProduct);
       Assert.assertNotNull(savedProduct.getProductId());

    }

    public void shouldDeleteProduct(){

        Long id = createdProduct.getProductId();
        productRepository.delete(id);
        Product deletedProduct = productRepository.findOne(id);
        Assert.assertEquals(null,deletedProduct);

    }


    public void shouldUpdateProduct(){

        createdProduct.setProductDesc("UPDATED");
        Product savedProduct = productRepository.save(createdProduct);
        Assert.assertTrue(null != savedProduct);
        Assert.assertTrue("UPDATED".equals(savedProduct.getProductDesc()));
    }

    public void shouldUpdateProductWithImages(){


        Image image;
        for(int i=0; i < 3 ; i++){
            image = new Image();
            image.setProductId(createdProduct.getProductId());
            image.setUrl("www.google/image"+i+".png");
            images.add(image);
        }
        createdProduct.setImages(images);

        Product savedProduct = productRepository.save(createdProduct);
        Assert.assertTrue(null != savedProduct);
        Assert.assertTrue(3 ==savedProduct.getImages().size());
    }


    @Test
    public void shouldFindAllProductsExcludingRelationships(){

        List<Product> list = productRepository.findAll();

        for(Product product : list){
            LOGGER.debug(" *** RESULT *** {}",product.toString());
        }

        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void shouldFindAllProductsIncludingRelationships(){



    }


    //Get specific product including its specified relationships (child product and/or images)
    @Test
    public void shouldFindProductIncludingRelationships(){

        List <Product> list = productRepository.findProductIncludingRelationships(1l); // product 1 is a parent, created by data.sql

        for(Product product : list){
            LOGGER.debug(" *** RESULT *** {}",product.toString());
        }
    }

    //Get specific product excluding its specified relationships (child product and/or images)
    @Test
    public void shouldFindProductExcludingRelationships() {

        Product productNoRelationship = productRepository.findOne(3l);
        LOGGER.debug(" *** RESULT *** {}",productNoRelationship.toString());


    }

}