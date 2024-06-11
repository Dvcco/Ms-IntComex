package com.ms.pruebatecnica.service.impl;

import com.ms.pruebatecnica.dto.CategoryDto;
import com.ms.pruebatecnica.exception.ExceptionPruebaTecnica;
import com.ms.pruebatecnica.model.CategoryModel;
import com.ms.pruebatecnica.repository.CategoryRepository;
import com.ms.pruebatecnica.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Base64;

import static com.ms.pruebatecnica.util.Constantes.CATEGORY_EXIST;
import static com.ms.pruebatecnica.util.Constantes.COD01;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Mono<CategoryDto> createCategory(CategoryDto categoryDto) {
        if (categoryRepository.findByCategoryName(categoryDto.getCategoryName().toLowerCase()).isPresent()) {
            throw new ExceptionPruebaTecnica(COD01, HttpStatus.BAD_REQUEST, CATEGORY_EXIST);
        }

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setCategoryName(categoryDto.getCategoryName());
        categoryModel.setDescription(categoryDto.getDescription());
        categoryModel.setPicture(categoryDto.getPicture());
        categoryRepository.save(categoryModel);
        return Mono.just(categoryDto);
    }
}
