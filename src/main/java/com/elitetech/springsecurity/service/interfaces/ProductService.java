package com.elitetech.springsecurity.service.interfaces;

import com.elitetech.springsecurity.dto.ProductDTO;
import com.elitetech.springsecurity.entity.Product;

import java.util.List;

public interface ProductService {
	  List<ProductDTO> getAllProducts();
	    List<ProductDTO> searchProducts(String name, Double minPrice, Double maxPrice, long categoryId);
	    ProductDTO addProduct(ProductDTO productDTO, long userId);
	    ProductDTO getProductById(long id);
	    void deleteProduct(long id);
	    public String placeBid(long productId, long userId, double bidAmount) ;

	    //String placeBid(long productId, long userId, double bidAmount);
}
