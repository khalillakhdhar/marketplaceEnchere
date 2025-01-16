package com.elitetech.springsecurity.repository;

import com.elitetech.springsecurity.entity.Auction;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
Optional<Auction> findByProductId(long id);
}
