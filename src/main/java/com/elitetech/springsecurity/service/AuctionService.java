package com.elitetech.springsecurity.service;

import com.elitetech.springsecurity.entity.Auction;
import com.elitetech.springsecurity.entity.Product;
import com.elitetech.springsecurity.entity.UserInfo;
import com.elitetech.springsecurity.repository.AuctionRepository;
import com.elitetech.springsecurity.repository.ProductRepository;
import com.elitetech.springsecurity.repository.UserInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserInfoRepository userRepository;

    public Auction createAuction(Long productId, double startingPrice, LocalDateTime startTime, LocalDateTime endTime) {
        Product product = productRepository.findById(productId)
                                           .orElseThrow(() -> new RuntimeException("Product not found"));
        Auction auction = new Auction();
        auction.setProduct(product);
        auction.setStartingPrice(startingPrice);
        auction.setCurrentBid(startingPrice);
        auction.setStartTime(startTime);
        auction.setEndTime(endTime);

        return auctionRepository.save(auction);
    }

    public Auction placeBid(Long auctionId, Long userId, double bidAmount) {
        Auction auction = auctionRepository.findById(auctionId)
                                           .orElseThrow(() -> new RuntimeException("Auction not found"));
        if (bidAmount <= auction.getCurrentBid()) {
            throw new IllegalArgumentException("Bid must be higher than the current bid.");
        }

        UserInfo user = userRepository.findById(userId)
                                      .orElseThrow(() -> new RuntimeException("User not found"));
        auction.setCurrentBid(bidAmount);
        auction.setHighestBidder(user);

        return auctionRepository.save(auction);
    }
}
