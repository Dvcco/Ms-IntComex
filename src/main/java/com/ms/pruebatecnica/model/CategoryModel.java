package com.ms.pruebatecnica.model;

import lombok.*;

import javax.persistence.*;

@Entity(name ="categories")
@ToString
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categories", schema = "db_pruebaintcomex")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private Integer categoryId;

    @Column(name = "CategoryName")
    private String categoryName;

    @Column(name = "Description")
    private String description;

    @Lob
    @Column(name = "Picture")
    private String picture;
}
