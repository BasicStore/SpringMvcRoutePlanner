package com.routeplanner.shopping.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.Purchase;
import com.routeplanner.shopping.repository.OrderRepository;
import com.routeplanner.shopping.repository.PurchaseRepository;


@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED)
@Service
public class PurchaseService {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseService.class);
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private BasketService basketService;
	
	
	public PurchaseService() {
		
	}

	public void save(Purchase purchase, Basket basket) {
		basketService.saveTickets(basket.getTickets());
		basketService.save(basket);
		orderRepository.save(purchase.getOrder());
		purchaseRepository.save(purchase);
		logger.debug("Purchase saved with id: " + purchase.getId());
	}
	
}

