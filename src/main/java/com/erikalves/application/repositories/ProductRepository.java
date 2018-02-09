package com.erikalves.application.repositories;


import com.erikalves.application.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {

    //finds specific product and all of its children projects (its images and children products)
    @Query("SELECT p FROM Product p where p.productId = :productId")
    List<Product> findProductIncludingRelationships(@Param("productId") Long id);

    // this will bring only "parent type" products, since the child products will be already included in each parent's Set of children products. \
    // Therefore, to pick up all, i only need to look for the root ones, the parents, since the ones that have a valid productParentId will eventually
    //come along with its root parent product.
    @Query("SELECT p FROM Product p where p.productParentId = null") //this means i am looking for root parent type products.
    List<Product> findAllProductsIncludingRelationships();


    //find all products, but excluding its children products.
    @Query("SELECT new com.erikalves.application.model.Product(p.productId, p.productParentId, p.productName, p.productDesc, p.productPrice, p.productCreatedTs, p.productUpdatedTs) FROM Product p ")
    List<Product> findAllExcludingRelationships();

    //find specific product, but excluding its children products.
   // @Query("SELECT distinct p.productId, p.productParentId, p.productName, p.productDesc, p.productPrice, p.productCreatedTs, p.productUpdatedTs FROM Product p WHERE  p.productId = :productId ")
    @Query("select new com.erikalves.application.model.Product(p.productId, p.productParentId, p.productName, p.productDesc, p.productPrice, p.productCreatedTs, p.productUpdatedTs) FROM Product p WHERE  p.productId = :productId")
    Product findProductExcludingRelationships(@Param("productId") Long id);


}
