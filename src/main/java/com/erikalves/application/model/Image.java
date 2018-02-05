package com.erikalves.application.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "IMAGE")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "IMAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "URL")
    private String url;


    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    @Override
    public String toString() {
        return "Image{" +
                "imageId='" + imageId + '\'' +
                "url='" + url + '\'' +
                "product='" + productId +
                '}';
    }


}
