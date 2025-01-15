package com.elitetech.springsecurity.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private long id;
    private String status;
    private Date date;

    @JsonIgnoreProperties({"commandes", "products"}) // Évite la boucle dans UserDTO
    private UserDTO user;

    @JsonIgnoreProperties({"user", "commandes"}) // Évite la boucle dans ProductDTO
    private List<ProductDTO> products;
}


