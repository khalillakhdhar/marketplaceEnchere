package com.elitetech.springsecurity.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private long id;
    private String name;
    private String description;

   // @JsonIgnoreProperties("category") // Évite la boucle dans ProductDTO
    private List<ProductDTO> products;
}

