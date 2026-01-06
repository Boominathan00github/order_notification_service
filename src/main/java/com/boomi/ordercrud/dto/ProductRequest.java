package com.boomi.ordercrud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProductRequest {
	
	@NotBlank(message = "{NotBlank.product.name}")
	private String name;
	private String category;
	@Positive(message = "{Positive.product.quantity}")
	private Integer quantity;
	@Positive(message = "{Positive.product.price}")
	private Double amount;
	
	
	
	
	// Getters and Setters	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
