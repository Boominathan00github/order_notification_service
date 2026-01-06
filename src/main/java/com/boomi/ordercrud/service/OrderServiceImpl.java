package com.boomi.ordercrud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.boomi.ordercrud.dto.OrderRequest;
import com.boomi.ordercrud.entity.Order;
import com.boomi.ordercrud.entity.Product;
import com.boomi.ordercrud.exception.NotificationFaildException;
import com.boomi.ordercrud.httpclient.NotificationClient;
import com.boomi.ordercrud.modelmapper.OrderMapper;
import com.boomi.ordercrud.repository.IOrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements IOrderService {

	private final IOrderRepository orderRepository;
	private final OrderMapper orderMapper;
	private final NotificationClient notificationClient;

	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	public OrderServiceImpl(IOrderRepository orderRepository, OrderMapper orderMapper,
			NotificationClient notificationClient) {

		this.orderRepository = orderRepository;
		this.orderMapper = orderMapper;
		this.notificationClient = notificationClient;
	}

	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public Order saveOrder(OrderRequest order) throws MethodArgumentNotValidException {

		log.info("OrderService: OrderService Called With OrderBy : ={}", order.getOrderBy());

		Order order1 = orderMapper.orderRequestToOrder(order);

		log.info("Order Service Called After Model Mapper");

		// Calculate Selected Products Amount
		Double calculateProductAmount = calculateProductsAmount(order1.getProducts(), order1);
		log.info("Order Service After Amount Calculated : ={} ", calculateProductAmount);

		order1.setTotalAmount(calculateProductAmount);

		log.info("OrderService: Before Returning the Order Object");

		Order savedOrder = orderRepository.save(order1);

		boolean orderNotification = notificationClient.sendNotification(savedOrder.getId());

		if (!orderNotification) {
			throw new NotificationFaildException("Notification service is Down!");

		}

		return savedOrder;
	}

	@Override
	public List<Order> getAllOrders() {
		log.info("OrderService: GetAllOrders Function Called");
		return orderRepository.findAll();
	}
	
	
	//Calculate all products amount helper f()
	private static Double calculateProductsAmount(List<Product> products, Order order) {

		Double sum = 0.0;
		int n = products.size();

		for (int i = 0; i < n; i++) {

			Double currentAmount = products.get(i).getAmount();

			sum += currentAmount;

			products.get(i).setOrder(order);

		}

		return sum;
	}

}
