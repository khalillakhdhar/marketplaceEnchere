package com.elitetech.springsecurity.controller;

import com.elitetech.springsecurity.entity.Auction;
import com.elitetech.springsecurity.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @PostMapping("/create")
    public ResponseEntity<Auction> createAuction(@RequestParam Long productId,
                                                 @RequestParam double startingPrice,
                                                 @RequestParam LocalDateTime startTime,
                                                 @RequestParam LocalDateTime endTime) {
        Auction auction = auctionService.createAuction(productId, startingPrice, startTime, endTime);
        return ResponseEntity.ok(auction);
    }

    @PostMapping("/bid")
    public ResponseEntity<Auction> placeBid(@RequestParam Long auctionId,
                                            @RequestParam Long userId,
                                            @RequestParam double bidAmount) {
        Auction auction = auctionService.placeBid(auctionId, userId, bidAmount);
        return ResponseEntity.ok(auction);
    }
}
