package com.boomi.ordercrud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boomi.ordercrud.dto.OrderRequest;
import com.boomi.ordercrud.entity.Order;
import com.boomi.ordercrud.service.IOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/orders")
public class OrderController {
	
	
	private final IOrderService orderService;
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	public OrderController(IOrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/hello")
	public String greet() {
		return "Hi, I'M Alive";
	}
	
	
	@PostMapping("/")
	public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequest order) 
			                                 throws MethodArgumentNotValidException{
		
		log.info("Order Controller Called with : ={}" , order.getOrderBy());
		
		return ResponseEntity.ok(orderService.saveOrder(order));
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<Order>> getAllOrders(){
		
		log.info("Order Controller Called in getAllOrders");
		
		return ResponseEntity.ok(orderService.getAllOrders());
	}
	
	
}
