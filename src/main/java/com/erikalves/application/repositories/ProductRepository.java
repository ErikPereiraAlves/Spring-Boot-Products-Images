package com.erikalves.application.repositories;


import com.erikalves.application.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {

    //finds itself and its childreen projects
    @Query("SELECT p FROM Product p where p.productParentId = :productId or p.productId = :productId")
    List<Product> findProductIncludingRelationships(@Param("productId") Long id);

    @Query("SELECT p FROM Product p where p.productParentId = :productId  and p.productId != :productId")
    List<Product> findProductRelationships(@Param("productId") Long id);

}
