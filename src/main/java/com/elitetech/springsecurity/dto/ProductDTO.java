package com.elitetech.springsecurity.dto;


import java.util.List;

import org.apache.catalina.User;

import com.elitetech.springsecurity.entity.Auction;
import com.elitetech.springsecurity.entity.UserInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private long id;
    private String name;
    private double price;
    private int stock;
    private String photo;
    private String description;

    @JsonIgnoreProperties("products")
    private CategoryDTO category; // L'objet CategoryDTO doit avoir un ID

    @JsonIgnoreProperties({"products", "commandes","auctions"}) // Évite la boucle dans UserDTO
    private UserDTO user;

    @JsonIgnoreProperties("products") // Évite la boucle dans OrderDTO
    private List<OrderDTO> commandes;
    @JsonIgnoreProperties({"product"})

    private Auction auction;
}

