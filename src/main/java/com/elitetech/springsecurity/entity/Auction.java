package com.elitetech.springsecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double startingPrice;

    private double currentBid;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ManyToOne
    @JsonIgnoreProperties("auction")
    private Product product;

    @ManyToOne
    @JsonIgnoreProperties({"auctions","products"})
    private UserInfo highestBidder;

}
