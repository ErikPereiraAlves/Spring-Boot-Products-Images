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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ImageRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageRepositoryTest.class);

    @Autowired(required=true)
    ImageRepository imageRepository;

    @Autowired (required=true)
    ProductRepository productRepository;

    Image image;

    List <Image> imageList = new ArrayList<>();

    @Before
    public void  begin(){

        List<Product> list = productRepository.findAll();

        if(null!= list && list.size() >0){
            for(Product product: list) {
                image = new Image();
                image.setUrl("www.google.com/test"+product.getProductId()+".png");
                image.setProductId(product.getProductId());
                imageList.add(image);
            }
        }
        else{
            Product product = createTestProduct();
            image = new Image();
            image.setUrl("www.google.com/test"+product.getProductId()+".png");
            image.setProductId(product.getProductId());
            imageList.add(image);
        }

    }

    public Product createTestProduct(){
        Product product = new Product();
        product = new Product();
        product.setProductParentId(1l);
        product.setProductName("Smartphone JUNIT just for image test cases");
        product.setProductDesc("Junit now manufactures smartphones thru image repository");
        product.setProductPrice(200.00);
        product.setProductCreatedTs(Util.getCurrentTs());
        product.setProductUpdatedTs(Util.getCurrentTs());

        Product savedProduct = productRepository.save(product);
        LOGGER.debug("saved product ID {}",savedProduct);
        Assert.assertNotNull(savedProduct.getProductId());

        return savedProduct;
    }


    @Test
    public void shouldCreateUpdateDeleteImage(){

        shouldCreateImage();
        shouldUpdateImage();
        shouldDeleteImage();
    }

    public void shouldCreateImage(){

        Image tempImage;
        for (Image image : imageList) {
            tempImage = imageRepository.save(image);
            LOGGER.debug("saved image ID {}", tempImage);
            Assert.assertNotNull(tempImage.getProductId());
            Assert.assertNotNull(tempImage.getImageId());

        }

    }

    public void shouldDeleteImage(){

          Image deletedImage = imageList.get(0);
          Long imageId = deletedImage.getImageId();
          Long productId = deletedImage.getProductId();
          if(null!=deletedImage){
              imageRepository.delete(deletedImage);
              imageList.remove(deletedImage);
          }

        List<Image> localImagesList = imageRepository.findAllImagesByProductId(productId);
        for (Image image: localImagesList){
            Assert.assertNotEquals(imageId,image.getImageId());
        }

        Image shouldBeImageNull = imageRepository.findOne(imageId);
        Assert.assertEquals(null,shouldBeImageNull);

    }


    public void shouldUpdateImage(){

        Image updatedImage;
        for(Image image: imageList){
            image.setUrl("www.google.com/updated"+ image.getProductId()+".png");
            updatedImage =imageRepository.save(image);
            Assert.assertTrue(null != updatedImage);
            assertTrue(updatedImage.getUrl().equals("www.google.com/updated"+ updatedImage.getProductId()+".png"));
        }


    }


}