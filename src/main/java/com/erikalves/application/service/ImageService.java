package com.erikalves.application.service;

import com.erikalves.application.model.Image;

import java.util.List;

public interface ImageService  extends GenericService <Image,Long>{


    List<Image> findAllImagesByProductId (Long id);


}
