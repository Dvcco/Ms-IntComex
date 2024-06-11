package com.ms.pruebatecnica.service;

import com.ms.pruebatecnica.dto.ProductDto;
import com.ms.pruebatecnica.dto.ProductWithPictureDto;
import com.ms.pruebatecnica.exception.ExceptionPruebaTecnica;
import com.ms.pruebatecnica.model.ProductModel;
import com.ms.pruebatecnica.repository.ProductRepository;
import com.ms.pruebatecnica.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testCreateProduct_Success() {
        ProductDto productDto = new ProductDto(1, 1, 1, "product", "ABC", new BigDecimal(1), 2, 3, 4, (byte) 0);
        when(productRepository.findByProductName("product")).thenReturn(Optional.empty());
        when(productRepository.save(any())).thenReturn(new ProductModel());
        ProductDto result = productService.createProduct(productDto).block();
        verify(productRepository, times(1)).findByProductName("product");
        verify(productRepository, times(1)).save(any());
    }
    @Test
    public void testCreateProduct_ProductExists() {
        ProductDto productDto = new ProductDto(1, 1, 1, "product", "ABC", new BigDecimal(1), 2, 3, 4, (byte) 0);
        when(productRepository.findByProductName("product")).thenReturn(Optional.of(new ProductModel()));
        assertThrows(ExceptionPruebaTecnica.class, () -> productService.createProduct(productDto).block());
    }

    @Test
    public void testListAllProducts() {
        int page = 1;
        int quantityProducts = 10;
        List<ProductModel> productList = Arrays.asList(
                new ProductModel(1, 1, 1, "Product 1", "1", new BigDecimal(10), 10, 20, 30, (byte) 0),
                new ProductModel(2, 2, 2, "Product 2", "1", new BigDecimal(20), 20, 30, 40, (byte) 0)
        );
        when(productRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(productList));
        Mono<List<ProductDto>> result = productService.listAllProducts(page, quantityProducts);
        List<ProductDto> productListDto = result.block();
        assertNotNull(productListDto);
    }

    @Test
    public void testSearchProductById_Found() {
        Integer productId = 1;
        ProductModel productModel = new ProductModel(2, 2, 2, "Product 2", "1", new BigDecimal(20), 20, 30, 40, (byte) 0);
        when(productRepository.findById(productId)).thenReturn(Optional.of(productModel));
        when(productRepository.findPictureByProductId(productId)).thenReturn("picture.jpg");
        Mono<ProductWithPictureDto> result = productService.searchProductById(productId);
        ProductWithPictureDto productDto = result.block();
    }

    @Test
    public void testSearchProductById_NotFound() {
        Integer productId = 1;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());
        Mono<ProductWithPictureDto> result = productService.searchProductById(productId);
        assertNull(result.block());
    }
}
