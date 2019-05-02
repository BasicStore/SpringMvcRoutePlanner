package com.routeplanner.shopping.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.routeplanner.shopping.Order;
import com.routeplanner.shopping.repository.OrderRepository;

@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED)
@Service
public class OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	public OrderService() {

	}

	public void save(Order order) {
		orderRepository.save(order);
		logger.debug("Order saved with id: " + order.getId());
	}
	
}

