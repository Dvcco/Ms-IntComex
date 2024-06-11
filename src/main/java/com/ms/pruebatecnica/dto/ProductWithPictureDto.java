package com.ms.pruebatecnica.dto;

import lombok.*;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductWithPictureDto {

    private Integer productId;

    private Integer supplierId;

    private Integer categoryId;

    private String productName;

    private String quantityPerUnit;

    private BigDecimal unitPrice;

    private Integer unitsInStock;

    private Integer unitsOnOrder;

    private Integer recorderLevel;

    private byte discontinued;

    private String picture;
}
