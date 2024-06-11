package com.ms.pruebatecnica.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ErrorDto {
    private String codigoError;
    private String descripcion;
}
