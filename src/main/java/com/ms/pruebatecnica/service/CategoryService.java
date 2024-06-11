package com.ms.pruebatecnica.service;

import com.ms.pruebatecnica.dto.CategoryDto;
import reactor.core.publisher.Mono;

public interface CategoryService {

    Mono<CategoryDto> createCategory(CategoryDto categoryDto);
}
