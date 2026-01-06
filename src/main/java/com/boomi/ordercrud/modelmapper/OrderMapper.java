package com.boomi.ordercrud.modelmapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.boomi.ordercrud.dto.OrderRequest;
import com.boomi.ordercrud.entity.Order;
import com.boomi.ordercrud.entity.Product;

@Component
public class OrderMapper {
	
	private final ProductMapper productMapper;
	
	public OrderMapper(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}
	
	public Order orderRequestToOrder(OrderRequest request) {
		
		
		Order order = new Order();
		
		List<Product> products = productMapper.listProductRequestToListProduct(request.getProductRequests());
		
		order.setOrderBy(request.getOrderBy());
		order.setProducts(products);
		
		
		return order;
	}

}
