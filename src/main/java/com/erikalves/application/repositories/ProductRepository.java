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
    @Query("SELECT p FROM Product p where p.productId = :productId")
    List<Product> findProductIncludingRelationships(@Param("productId") Long id);

    @Query("SELECT p FROM Product p where p.productParentId = null")
    List<Product> findAllProductsIncludingRelationships();


    //find all products, but excluding its children products.
    @Query("SELECT new com.erikalves.application.model.Product(p.productId, p.productParentId, p.productName, p.productDesc, p.productPrice, p.productCreatedTs, p.productUpdatedTs) FROM Product p ")
    List<Product> findAllExcludingRelationships();

    //find product, but excluding its children products.
   // @Query("SELECT distinct p.productId, p.productParentId, p.productName, p.productDesc, p.productPrice, p.productCreatedTs, p.productUpdatedTs FROM Product p WHERE  p.productId = :productId ")
    @Query("select new com.erikalves.application.model.Product(p.productId, p.productParentId, p.productName, p.productDesc, p.productPrice, p.productCreatedTs, p.productUpdatedTs) FROM Product p WHERE  p.productId = :productId")
    Product findProductExcludingRelationships(@Param("productId") Long id);
}
