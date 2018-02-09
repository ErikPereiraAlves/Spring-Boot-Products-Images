package com.erikalves.application.service;

import com.erikalves.application.model.Image;
import com.erikalves.application.model.Product;
import com.erikalves.application.repositories.ImageRepository;
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
public class ImageServiceImplTest {


    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImplTest.class);


    @Autowired
    @Qualifier(value = "ProductService")
    ProductService productService;

    @Autowired
    @Qualifier(value = "ImageService")
    ImageService imageService;

    @Autowired(required = true)
    ProductRepository productRepository;

    @Autowired(required = true)
    ImageRepository imageRepository;

    Product product;

    Product savedProduct;

    Image image;

    Image savedImage;


    @Before
    public void begin() {

        product = new Product();
        product.setProductParentId(1l);
        product.setProductName("Smartphone Product service impl test");
        product.setProductDesc("ProductServiceImplTest");
        product.setProductPrice(100.55);
        product.setProductCreatedTs(Util.getCurrentTs());
        product.setProductUpdatedTs(Util.getCurrentTs());

        savedProduct = productService.save(product);
        LOGGER.debug("saved product ID {}", savedProduct.getProductId());
        Assert.assertNotNull(savedProduct.getProductId());

        image = new Image();
        image.setUrl("www.google.com/imageservice.png");
        image.setProduct(savedProduct);
        savedImage = imageService.save(image);

    }

    @Test
    public void getId() {

        Long imageId = imageService.getId(savedImage);
        Assert.assertNotNull(imageId);
    }

    @Test
    public void getRepository() {

        CrudRepository<Image, Long> crudRepository = imageService.getRepository();
        Assert.assertNotNull(crudRepository);
        Assert.assertTrue(EqualsBuilder.reflectionEquals(imageRepository, crudRepository));
    }

    @Test
    public void findAllImagesByProductId() {

        List<Image> list = imageService.findAllImagesByProductId(savedProduct.getProductId());
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }
}