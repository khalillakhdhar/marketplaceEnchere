package com.elitetech.springsecurity.service;

import com.elitetech.springsecurity.dto.ProductDTO;
import com.elitetech.springsecurity.entity.Auction;
import com.elitetech.springsecurity.entity.Category;
import com.elitetech.springsecurity.entity.Product;
import com.elitetech.springsecurity.entity.UserInfo;
import com.elitetech.springsecurity.mapper.ProductMapper;
import com.elitetech.springsecurity.repository.AuctionRepository;
import com.elitetech.springsecurity.repository.CategoryRepository;
import com.elitetech.springsecurity.repository.ProductRepository;
import com.elitetech.springsecurity.repository.UserInfoRepository;
import com.elitetech.springsecurity.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private UserInfoRepository userRepository;
    @Autowired
    private AuctionRepository auctionRepository;

   
    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::convertToDto)
                .collect(Collectors.toList());
    }
    

    @Override
    public List<ProductDTO> searchProducts(String name, Double minPrice, Double maxPrice, long categoryId) {
        return productRepository.findAll().stream()
                .filter(product -> (name == null || product.getName().toLowerCase().contains(name.toLowerCase())) &&
                                   (minPrice == null || product.getPrice() >= minPrice) &&
                                   (maxPrice == null || product.getPrice() <= maxPrice) &&
                                   (categoryId == 0 || (product.getCategory() != null && product.getCategory().getId() == categoryId)))
                .map(ProductMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO, long userId) {
        // Récupérer l'utilisateur
        UserInfo owner = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Récupérer la catégorie
        Category category = categoryRepository.findById(productDTO.getCategory().getId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        // Créer un nouveau produit
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setPhoto(productDTO.getPhoto());
        product.setUser(owner);
        product.setCategory(category);

        // Sauvegarder le produit
        Product savedProduct = productRepository.save(product);

        // Créer une enchère associée au produit
        Auction auction = new Auction();
        auction.setProduct(savedProduct);
        auction.setStartingPrice(product.getPrice()); // Prix initial basé sur le prix du produit
        auction.setCurrentBid(product.getPrice());
        auction.setStartTime(LocalDateTime.now());
        auction.setEndTime(LocalDateTime.now().plusDays(7)); // Durée par défaut de 7 jours
        auctionRepository.save(auction);

        return ProductMapper.convertToDto(savedProduct);
    }


    @Override
    public ProductDTO getProductById(long id) {
        return productRepository.findById(id)
                .map(ProductMapper::convertToDto)
                .orElse(null);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
    public String placeBid(long productId, long userId, double bidAmount) {
        // Récupérer l'enchère associée au produit
        Auction auction = auctionRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("Auction not found for product"));

        // Vérifier si le montant de l'offre est supérieur à l'enchère actuelle
        if (bidAmount <= auction.getCurrentBid()) {
            return "Bid amount must be higher than the current bid";
        }

        // Récupérer l'utilisateur qui place l'enchère
        UserInfo user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Mettre à jour l'enchère
        auction.setCurrentBid(bidAmount);
        auction.setHighestBidder(user);

        // Sauvegarder l'enchère mise à jour
        auctionRepository.save(auction);

        return "Success";
    }

   
}
