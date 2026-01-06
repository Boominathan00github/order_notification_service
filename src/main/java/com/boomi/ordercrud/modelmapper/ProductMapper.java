package com.boomi.ordercrud.modelmapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.boomi.ordercrud.dto.ProductRequest;
import com.boomi.ordercrud.entity.Product;


@Component
public class ProductMapper {

	
	public Product productRequestToProduct(ProductRequest request) {
		
		Product product = new Product();
		
		product.setName(request.getName());
		product.setCategory(request.getCategory());
		product.setAmount(request.getAmount());
		product.setQuantity(request.getQuantity());
		
		return product;
	}
	
	
	public List<Product> listProductRequestToListProduct(List<ProductRequest> requests) {

		List<Product> products = new ArrayList<>();

		for (ProductRequest request : requests) {
			products.add(
					new Product(request.getName(), 
							request.getCategory(), 
							request.getQuantity(), 
							request.getAmount()));
		}

		return products;
	}
	
}
