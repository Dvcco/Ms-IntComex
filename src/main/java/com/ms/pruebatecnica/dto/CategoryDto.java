package com.ms.pruebatecnica.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDto {

    @JsonIgnore
    private Integer categoryId;

    private String categoryName;

    private String description;

    private String picture;
}
