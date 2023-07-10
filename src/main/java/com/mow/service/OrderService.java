package com.mow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mow.entity.OrderRequest;
import com.mow.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	public OrderRequest saveOrder(OrderRequest order) {
		
		return orderRepository.save(order);
	}
	
	public List<OrderRequest> showOrder() {
		return orderRepository.findAll();
	}
	
	 public OrderRequest getOrderById(Long orderId) {
	        return orderRepository.findById(orderId).orElse(null);
	    }
}