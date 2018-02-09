package com.erikalves.application.repositories;


import com.erikalves.application.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {


    @Query("SELECT i FROM Image i where i.product.productId = :productId")
    List<Image> findAllImagesByProductId(@Param("productId") Long id);

}
