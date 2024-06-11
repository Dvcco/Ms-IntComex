package com.ms.pruebatecnica.service.impl;

import com.ms.pruebatecnica.dto.ProductDto;
import com.ms.pruebatecnica.dto.ProductWithPictureDto;
import com.ms.pruebatecnica.exception.ExceptionPruebaTecnica;
import com.ms.pruebatecnica.model.ProductModel;
import com.ms.pruebatecnica.repository.ProductRepository;
import com.ms.pruebatecnica.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import static com.ms.pruebatecnica.util.Constantes.COD02;
import static com.ms.pruebatecnica.util.Constantes.PRODUCT_EXIST;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Mono<ProductDto> createProduct(ProductDto productDto) {
        if (productRepository.findByProductName(productDto.getProductName().toLowerCase()).isPresent()) {
            throw new ExceptionPruebaTecnica(COD02, HttpStatus.BAD_REQUEST, PRODUCT_EXIST);
        }

        ProductModel productModel = new ProductModel();
        productModel.setSupplierId(productDto.getSupplierId());
        productModel.setCategoryId(productDto.getCategoryId());
        productModel.setProductName(productDto.getProductName());
        productModel.setQuantityPerUnit(productDto.getQuantityPerUnit());
        productModel.setUnitPrice(productDto.getUnitPrice());
        productModel.setUnitsInStock(productDto.getUnitsInStock());
        productModel.setUnitsOnOrder(productDto.getUnitsOnOrder());
        productModel.setRecorderLevel(productDto.getRecorderLevel());
        productModel.setDiscontinued(productDto.getDiscontinued());
        productRepository.save(productModel);
        return Mono.just(productDto);
    }

    @Override
    public Mono<List<ProductDto>> listAllProducts(int page, int quantityProducts) {
        PageRequest request = PageRequest.of(page, quantityProducts);
        Page<ProductModel> listProduct = productRepository.findAll(request);
        return Mono.just(listProduct.getContent().stream().map(dto -> ProductDto.builder()
                .productId(dto.getProductId())
                .supplierId(dto.getSupplierId())
                .categoryId(dto.getCategoryId())
                .productName(dto.getProductName())
                .quantityPerUnit(dto.getQuantityPerUnit())
                .unitPrice(dto.getUnitPrice())
                .unitsInStock(dto.getUnitsInStock())
                .unitsOnOrder(dto.getUnitsOnOrder())
                .recorderLevel(dto.getRecorderLevel())
                .discontinued(dto.getDiscontinued())
                .build()).collect(Collectors.toList()));
    }

    @Override
    public Mono<ProductWithPictureDto> searchProductById(Integer productId) {
        return Mono.justOrEmpty(productRepository.findById(productId))
                .map(productModel -> {
                    String picture = productRepository.findPictureByProductId(productId);
                    ProductWithPictureDto productDto = new ProductWithPictureDto();
                    productDto.setProductId(productModel.getProductId());
                    productDto.setSupplierId(productModel.getSupplierId());
                    productDto.setCategoryId(productModel.getCategoryId());
                    productDto.setProductName(productModel.getProductName());
                    productDto.setQuantityPerUnit(productModel.getQuantityPerUnit());
                    productDto.setUnitPrice(productModel.getUnitPrice());
                    productDto.setUnitsInStock(productModel.getUnitsInStock());
                    productDto.setUnitsOnOrder(productModel.getUnitsOnOrder());
                    productDto.setRecorderLevel(productModel.getRecorderLevel());
                    productDto.setDiscontinued(productModel.getDiscontinued());
                    productDto.setPicture(picture);
                    return productDto;
                });
    }
}
