package com.erikalves.application.repositories;

import com.erikalves.application.model.Image;
import com.erikalves.application.model.Product;
import com.erikalves.application.service.ImageService;
import com.erikalves.application.service.ProductService;
import com.erikalves.application.utils.Util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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


    Product createdProduct, updatableProduct;

    Long productId = 99l;

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
        createdProduct.setImages(images);

        Image image;
        for(int i=0; i < 3 ; i++){
            image = new Image();
            image.setProductId(createdProduct.getProductId());
            image.setUrl("www.google/image"+i+".png");
            images.add(image);
        }


    }


    @Test
    public void shouldCreateUpdateDeleteProduct(){

        shouldCreateProduct();
        shouldUpdateProduct();
        shouldDeleteProduct();
    }

    public void shouldCreateProduct(){

       Product savedProduct = productRepository.save(createdProduct);
       LOGGER.debug("saved product ID {}",savedProduct);
       // Assert.assertEquals(savedProduct,createdProduct);


    }

    public void shouldDeleteProduct(){

        productRepository.delete(productId);
        Product deletedProduct = productRepository.findOne(productId);
        Assert.assertEquals(null,deletedProduct);
        //LOGGER.debug(deletedProduct.toString());
    }


    public void shouldUpdateProduct(){

        createdProduct.setProductDesc("UPDATED");
        Product savedProduct = productRepository.save(createdProduct);
        Assert.assertTrue(null != savedProduct);
        Assert.assertTrue("UPDATED".equals(savedProduct.getProductDesc()));
    }

    /*
    @Test
    public void shouldFindAllProductsExcludingRelationships(){

        List<Product> list = productRepository.findAll();

        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void shouldFindAllProductsIncludingRelationships(){



    }


    @Test
    public void shouldFindProductIncludingRelationships(){

        List <Product> product = productRepository.findAllChildrenProductsByProductParentId(1l);
    }

    @Test
    public void shouldFindProductExcludingRelationships() {

        Product productNoRelationship = productRepository.findOne(3l);



    }
    */
}