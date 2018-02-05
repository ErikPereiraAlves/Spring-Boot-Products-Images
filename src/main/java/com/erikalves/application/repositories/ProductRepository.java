package com.erikalves.application.repositories;


import com.erikalves.application.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {


    @Query("SELECT p FROM Product p where p.productParentId = :productParentId")
    List<Product> findAllChildrenProductsByProductParentId(@Param("productParentId") Long id);

}
