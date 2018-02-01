package com.erikalves.application.model;

import java.io.Serializable;
import java.sql.Date;
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
    private String producParentId;

    @Column (name = "PRODUCT_NAME")
    private String productName;

    @Column (name = "PRODUCT_DESC")
    private String productDesc;

    @Column (name = "PRODUCT_PRICE")
    private Double productPrice;

    @Column (name = "PRODUCT_CREATED_TS")
    private Date productCreatedTs;

    @Column (name = "PRODUCT_UPDATED_TS")
    private Date productUpdatedTs;


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProducParentId() {
        return producParentId;
    }

    public void setProducParentId(String producParent) {
        this.producParentId = producParentId;
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

    public Date getProductCreatedTs() {
        return productCreatedTs;
    }

    public void setProductCreatedTs(Date productCreatedTs) {
        this.productCreatedTs = productCreatedTs;
    }

    public Date getProductUpdatedTs() {
        return productUpdatedTs;
    }

    public void setProductUpdatedTs(Date productUpdatedTs) {
        this.productUpdatedTs = productUpdatedTs;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id='" + productId + '\'' +
                "parent='" + producParentId + '\'' +
                "desc='" + productDesc + '\'' +
                "price='" + productPrice + '\'' +
                "created TS='" + productCreatedTs + '\'' +
                "updated TS='" + productUpdatedTs +
                '}';
    }
}
