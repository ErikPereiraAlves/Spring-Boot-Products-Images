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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false,
            foreignKey = @ForeignKey(name = "IMAGE_PRODUCT_FK"))
    private Product product;

    @Column(name = "URL")
    private String url;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
                "id='" + imageId + '\'' +
                "url='" + url + '\'' +
                "Product='" + product.getProductId() +
                '}';
    }


}
