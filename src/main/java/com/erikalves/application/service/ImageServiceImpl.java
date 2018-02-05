package com.erikalves.application.service;

import com.erikalves.application.model.Image;
import com.erikalves.application.repositories.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ImageService")
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;


    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Override
    public Long getId(Image entity) {
        return entity.getImageId();
    }

    @Override
    public CrudRepository<Image, Long> getRepository() {
        return this.imageRepository;
    }


    @Override
    public List<Image> findAllImagesByProductId(Long id) {
        return imageRepository.findAllImagesByProductId(id);
    }
}
