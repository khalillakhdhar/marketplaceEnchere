package com.elitetech.springsecurity.dto;



import java.util.List;
import java.util.Set;

import com.elitetech.springsecurity.entity.Commande;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String name;
    private String email;
    private String roles;
    private String password;

    @JsonIgnoreProperties({"user", "commandes"}) // Évite la boucle dans ProductDTO
    private List<ProductDTO> products;

    @JsonIgnoreProperties({"user", "products"}) // Évite la boucle dans OrderDTO
    private List<OrderDTO> commandes;
}


