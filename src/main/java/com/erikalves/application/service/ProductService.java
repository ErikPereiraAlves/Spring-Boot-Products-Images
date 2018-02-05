package com.erikalves.application.service;


import com.erikalves.application.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService extends GenericService <Product,Long>{


    List<Product> findAllChildrenProductsByProductParentId(Long id);


}
