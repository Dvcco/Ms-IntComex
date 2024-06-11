package com.ms.pruebatecnica.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionPruebaTecnica extends RuntimeException {

    private static final long serialVersionUID = -5778897315177742714L;

    private final String codigoError;

    private final HttpStatus codeHttp;

    private final String descripcion;
}