package com.ms.pruebatecnica.controller.advice;

import com.ms.pruebatecnica.dto.ErrorDto;
import com.ms.pruebatecnica.exception.ExceptionPruebaTecnica;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(value = ExceptionPruebaTecnica.class)
    public ResponseEntity<ErrorDto> runtimeExceptionHandler(ExceptionPruebaTecnica ex) {
        return ResponseEntity.status(ex.getCodeHttp()).body(ErrorDto.builder().codigoError(ex.getCodigoError()).descripcion(ex.getDescripcion()).build());
    }
}
