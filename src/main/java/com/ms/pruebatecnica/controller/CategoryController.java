package com.ms.pruebatecnica.controller;

import com.ms.pruebatecnica.dto.CategoryDto;
import com.ms.pruebatecnica.exception.ExceptionPruebaTecnica;
import com.ms.pruebatecnica.service.CategoryService;
import com.ms.pruebatecnica.service.impl.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.ms.pruebatecnica.util.Constantes.COD03;
import static com.ms.pruebatecnica.util.Constantes.TOKEN_EXPIRED;

@RestController
@RequestMapping("/Category")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "Guardar Categoria", response = CategoryDto.class, httpMethod = "POST")
    @ApiResponses({@ApiResponse(code = 200, message = " Operacion exitosa, crea una Categoria correctamente")})
    @PostMapping(value = "/createCategory")
    public Mono<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto, @RequestHeader("Authorization") String token) {
        tokenService.validateTokenExpired(token);
        return categoryService.createCategory(categoryDto);
    }
}
