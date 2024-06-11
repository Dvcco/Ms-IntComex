package com.ms.pruebatecnica.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name ="products")
@ToString
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products", schema = "db_pruebaintcomex")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Integer productId;

    @Column(name = "SupplierID")
    private Integer supplierId;

    @Column(name = "CategoryID")
    private Integer categoryId;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "QuantityPerUnit")
    private String quantityPerUnit;

    @Column(name = "UnitPrice")
    private BigDecimal unitPrice;

    @Column(name = "UnitsInStock")
    private Integer unitsInStock;

    @Column(name = "UnitsOnOrder")
    private Integer unitsOnOrder;

    @Column(name = "RecorderLevel")
    private Integer recorderLevel;

    @Column(name = "Discontinued")
    private byte discontinued;
}
