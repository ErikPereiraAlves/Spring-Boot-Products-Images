package com.erikalves.application.repositories;


import com.erikalves.application.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {

    //finds the product itself and all of its children projects (both including its images)
    @Query("SELECT p FROM Product p where p.productParentId = :productId or p.productId = :productId")
    List<Product> findProductIncludingRelationships(@Param("productId") Long id);

    //find only a product's children products (and products images) excluding the product itself.
    @Query("SELECT p FROM Product p where p.productParentId = :productId  and p.productId != :productId")
    List<Product> findProductRelationships(@Param("productId") Long id);

    //find all products, but excluding its children products.
    @Query("SELECT p.productId, p.productParentId, p.productName, p.productDesc,p.productPrice,p.productCreatedTs,p.productUpdatedTs FROM Product p ")
    List<Object> findAllExcludingRelationships();

    //find product, but excluding its children products.
    @Query("SELECT distinct p.productId, p.productParentId, p.productName, p.productDesc, p.productPrice, p.productCreatedTs, p.productUpdatedTs FROM Product p WHERE  p.productId = :productId ")
    Object findProductExcludingRelationships(@Param("productId") Long id);
}
