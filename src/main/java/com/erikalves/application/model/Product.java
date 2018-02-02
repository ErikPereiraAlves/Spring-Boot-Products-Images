package com.erikalves.application.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="PRODUCT")
public class Product implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @Column (name = "PRODUCT_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long productId;

    @Column (name = "PRODUCT_PARENT_ID")
    private Long productParentId;

    @Column (name = "PRODUCT_NAME")
    private String productName;

    @Column (name = "PRODUCT_DESC")
    private String productDesc;

    @Column (name = "PRODUCT_PRICE")
    private Double productPrice;

    @Column (name = "PRODUCT_CREATED_TS")
    private Timestamp productCreatedTs;

    @Column (name = "PRODUCT_UPDATED_TS")
    private Timestamp productUpdatedTs;

    @OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
    private Set<Image> images;


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductParentId() {
        return productParentId;
    }

    public void setProductParentId(Long productParentId) {
        this.productParentId = productParentId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Timestamp getProductCreatedTs() {
        return productCreatedTs;
    }

    public void setProductCreatedTs(Timestamp productCreatedTs) {
        this.productCreatedTs = productCreatedTs;
    }

    public Timestamp getProductUpdatedTs() {
        return productUpdatedTs;
    }

    public void setProductUpdatedTs(Timestamp productUpdatedTs) {
        this.productUpdatedTs = productUpdatedTs;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                "productParentId='" + productParentId + '\'' +
                "productName='" + productName + '\'' +
                "productDesc='" + productDesc + '\'' +
                "productPrice='" + productPrice + '\'' +
                "productCreatedTs='" + productCreatedTs + '\'' +
                "productUpdatedTs='" + productUpdatedTs + '\'' +
                "productUpdatedTs='" + productUpdatedTs +
                '}';
    }
}
