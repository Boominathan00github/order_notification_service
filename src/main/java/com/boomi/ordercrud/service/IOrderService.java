package com.boomi.ordercrud.service;

import java.util.List;

import org.springframework.web.bind.MethodArgumentNotValidException;

import com.boomi.ordercrud.dto.OrderRequest;
import com.boomi.ordercrud.entity.Order;

public interface IOrderService {

	public Order saveOrder(OrderRequest order) throws MethodArgumentNotValidException;
	
	public List<Order> getAllOrders();
	
}
