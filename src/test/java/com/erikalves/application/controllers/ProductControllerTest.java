package com.erikalves.application.controllers;

import com.erikalves.application.model.Product;
import com.erikalves.application.service.ProductService;
import com.erikalves.application.service.ProductServiceImpl;
import com.erikalves.application.utils.RestApiResponseTo;
import com.erikalves.application.utils.Util;
import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {


    private static final Logger LOGGER = LoggerFactory.getLogger(ProductControllerTest.class);

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Autowired
    @Qualifier(value = "ProductService")
    ProductService productService;

    String productJson;

    Product savedProduct;

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Before
    public void init() {
        createTestProduct();
    }

    private void createTestProduct() {
        Product product = new Product();
        product.setProductParentId(1l);
        product.setProductName("Smartphone controller integration tests");
        product.setProductDesc("controller integration tests for product");
        product.setProductPrice(200.00);
        product.setProductCreatedTs(Util.getCurrentTs());
        product.setProductUpdatedTs(Util.getCurrentTs());
        savedProduct = productService.save(product);
        productJson = new Gson().toJson(savedProduct);
        LOGGER.debug("Json representation of a the created Product {} ", productJson);
    }


    @Test
    public void shouldFindAllProductsExcludingRelationships() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/store/api/v1/products/exclude"),
                HttpMethod.GET,entity,String.class);

        LOGGER.debug("Response results {}",response.getBody());
        Assert.assertTrue(!response.getBody().contains("Internal Server Error"));

    }

    @Test
    public void shouldFindAllProductsIncludingRelationships() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/store/api/v1/products/include"),
                HttpMethod.GET,entity,String.class);

        LOGGER.debug("Response results {}",response.getBody());
        Assert.assertTrue(response.getBody().contains("\"productId\":1"));
    }


    @Test
    public void shouldGetProductExcludingRelationships() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/store/api/v1/products/exclude/1"),
                HttpMethod.GET,entity,String.class);

        LOGGER.debug("Response results {}",response.getBody());
        Assert.assertTrue(!response.getBody().contains("Internal Server Error"));

    }

    @Test
    public void shouldGetProductIncludingRelationships() {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/store/api/v1/products/include/1"),
                HttpMethod.GET,entity,String.class);

        LOGGER.debug("Response results {}",response.getBody());
        Assert.assertTrue(response.getBody().contains("\"productId\":1"));
    }

    @Test
    public void shouldDeleteProduct() {
    }

    @Test
    public void shouldCreateProduct() {


    }

    @Test
    public void shouldUpdateProduct() {
    }
}