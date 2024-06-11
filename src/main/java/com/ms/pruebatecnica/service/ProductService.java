package com.ms.pruebatecnica.service;

import com.ms.pruebatecnica.dto.ProductDto;
import com.ms.pruebatecnica.dto.ProductWithPictureDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductService {

    Mono<ProductDto> createProduct (ProductDto productDto);

    Mono<List<ProductDto>> listAllProducts(int page, int quantityProducts);

    Mono<ProductWithPictureDto> searchProductById(Integer productId);
}
