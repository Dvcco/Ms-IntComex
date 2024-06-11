package com.ms.pruebatecnica.controller;

import com.ms.pruebatecnica.service.impl.TokenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Security")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @ApiOperation(value = "Generar Token", httpMethod = "POST")
    @ApiResponses({ @ApiResponse(code = 200,message = " Operacion exitosa, se genera el token correctamente")})
    @PostMapping("/generateToken")
    public Mono<ResponseEntity<String>> generateToken(@RequestBody String token) {
        String tokenGenerado = tokenService.generateToken(token);
        return Mono.just(ResponseEntity.ok("Token: " + tokenGenerado));
    }

    @ApiOperation(value = "Validar Token", httpMethod = "POST")
    @ApiResponses({ @ApiResponse(code = 200,message = " Operacion exitosa, se valida el estado del token")})
    @GetMapping("/validateToken")
    public Mono<String> validateToken(@RequestHeader("Authorization") String token) {
        return Mono.just(tokenService.validateToken(token));
    }
}