package com.boomi.ordercrud.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderRequest {
	
	@NotBlank(message = "{NotBlank.order.orderBy}")
	private String orderBy;
	
	@NotNull(message = "{NotEmpty.order.products}")
	private List<@Valid ProductRequest> productRequests = new ArrayList<>();
	
	
// Getters and Setters	
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public List<ProductRequest> getProductRequests() {
		return productRequests;
	}
	public void setProductRquests(List<ProductRequest> productRequests) {
		this.productRequests = productRequests;
	}
	

}
