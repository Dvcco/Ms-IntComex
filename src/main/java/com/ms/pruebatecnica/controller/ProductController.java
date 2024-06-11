package com.ms.pruebatecnica.controller;

import com.ms.pruebatecnica.dto.ProductDto;
import com.ms.pruebatecnica.dto.ProductWithPictureDto;
import com.ms.pruebatecnica.exception.ExceptionPruebaTecnica;
import com.ms.pruebatecnica.service.ProductService;
import com.ms.pruebatecnica.service.impl.TokenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.ms.pruebatecnica.util.Constantes.COD03;
import static com.ms.pruebatecnica.util.Constantes.TOKEN_EXPIRED;

@RestController
@RequestMapping("/Product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Guardar Producto", response = ProductDto.class, httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 200, message = " Operacion exitosa, crea una Producto correctamente")})
    @PostMapping(value = "/createProduct")
    public Mono<ProductDto> createProduct(@RequestBody ProductDto productDto, @RequestHeader("Authorization") String token) {
        tokenService.validateTokenExpired(token);
        return productService.createProduct(productDto);
    }

    @ApiOperation(value = "Lista todos los Productos", response = ProductDto.class, httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = " Operacion exitosa, lista todos los productos correctamente")})
    @GetMapping(value = "/Products")
    public Mono<List<ProductDto>> listAllProducts(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int quantityProducts,
                                                  @RequestHeader("Authorization") String token) {
        tokenService.validateTokenExpired(token);
        return productService.listAllProducts(page, quantityProducts);
    }

    @ApiOperation(value = "Lista todos los Productos mediante su ID", response = ProductWithPictureDto.class, httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 200, message = " Operacion exitosa, lista todos los productos correctamente por ID")})
    @GetMapping(value = "/Products/{id}")
    public Mono<ProductWithPictureDto> searchProductById(@RequestParam int id,
                                                         @RequestHeader("Authorization") String token) {
        tokenService.validateTokenExpired(token);
        return productService.searchProductById(id);
    }

}

