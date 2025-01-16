package com.elitetech.springsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.elitetech.springsecurity.dto.ProductDTO;
import com.elitetech.springsecurity.entity.Product;
import com.elitetech.springsecurity.service.interfaces.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO, @RequestParam long userId) {
        ProductDTO newProduct = productService.addProduct(productDTO, userId);
        return ResponseEntity.ok(newProduct);
    }
    @PostMapping("/{productId}/bid")
    public ResponseEntity<String> placeBid(
            @PathVariable long productId,
            @RequestParam long userId,
            @RequestParam double bidAmount) {
        String result = productService.placeBid(productId, userId, bidAmount);
        return result.equals("Success") 
                ? ResponseEntity.ok("Bid placed successfully") 
                : ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable @Min(1) long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getOneProduct(@PathVariable @Min(1) long id) {
        ProductDTO product = productService.getProductById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false, defaultValue = "0") long categoryId) {

        List<ProductDTO> products = productService.searchProducts(
                name != null ? name : "",
                minPrice,
                maxPrice,
                categoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}

