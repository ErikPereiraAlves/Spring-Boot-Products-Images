package com.erikalves.application.controllers;

import com.erikalves.application.model.Image;
import com.erikalves.application.model.Product;
import com.erikalves.application.service.ImageService;
import com.erikalves.application.service.ProductService;
import com.erikalves.application.utils.Util;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImageControllerTest {


    private static final Logger LOGGER = LoggerFactory.getLogger(ProductControllerTest.class);

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Autowired
    @Qualifier(value = "ImageService")
    ImageService imageService;

    @Autowired
    @Qualifier(value = "ProductService")
    ProductService productService;

    String productJson;

    Product savedProduct;

    Image savedImage;


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


    @Before
    public void init() {
        createTestProduct();
        createTestImage();
    }

    private void createTestProduct() {
        Product product = new Product();
        product.setProductParentId(1l);
        product.setProductName("Smartphone controller integration tests");
        product.setProductDesc("controller integration tests for product");
        product.setProductPrice(200.00);
        product.setProductCreatedTs(Util.getCurrentDate());
        product.setProductUpdatedTs(Util.getCurrentDate());
        savedProduct = productService.save(product);
        productJson = Util.getGson().toJson(savedProduct);
        LOGGER.debug("Json representation of a the created Product {} ", productJson);
    }

    private void createTestImage(){
        Image image = new Image();
        image.setProduct(savedProduct);
        image.setUrl("www.post-test.com/image.png");
        savedImage = imageService.save(image);
    }

    @Test
    public void shouldGetImagesProduct() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/store/api/v1/images/product/"+2),
                HttpMethod.GET,entity,String.class);

        LOGGER.debug("Response results {}",response.getBody());
        Assert.assertFalse(response.getBody().contains("Internal Server Error"));
        Assert.assertTrue(response.getBody().contains("{\"results\":[{\"imageId\":"));
    }

    @Test
    public void shouldDeleteImage() {

        restTemplate.delete(createURLWithPort("/store/api/v1/images/"+savedImage.getImageId()));
        Image deletedImage = imageService.get(savedImage.getImageId());
        LOGGER.debug("Response results {}",deletedImage);
        Assert.assertNull(deletedImage);
    }

    @Test
    public void shouldCreateImage() {

        Image image = new Image();
        image.setUrl("www.post-test.com/image.png");

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(createURLWithPort("/store/api/v1/images/3") , image, String.class);

        LOGGER.debug("Response results {}",responseEntity.getBody().toString());
        Assert.assertNotNull(responseEntity);
    }

    @Test
    public void shouldUpdateImage() {

        Long imageId = savedImage.getImageId();
        savedImage.setUrl("UPDATED");
        restTemplate.put(createURLWithPort("/store/api/v1/images/") , savedImage, String.class);

        Image updatedImage = imageService.get(imageId);
        Assert.assertNotNull(updatedImage);
        Assert.assertEquals(updatedImage.getImageId() , imageId);
        LOGGER.debug("Response results {}",updatedImage.getUrl());

    }
}