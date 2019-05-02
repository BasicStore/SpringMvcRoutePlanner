package com.routeplanner.shopping.service;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.Ticket;
import com.routeplanner.shopping.repository.BasketRepository;


@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED) 
@Service
public class BasketService {

	private static final Logger logger = LoggerFactory.getLogger(BasketService.class);
	
	@Autowired
	private BasketRepository<Basket> basketRepository;
	
	public BasketService() {
		
	}
	
	public void save(Basket basket) {
		basketRepository.save(basket);
		logger.debug("Basket saved with id: " + basket.getId());
	}
	
	public Basket findOpenBasketForUser(int userId) {
		logger.debug("Searching for baskets for user with id: " + userId);
		Optional<Basket> basket = basketRepository.findOpenBasketForUser(userId);
		String basketFoundMsg = basket.isPresent() ? String.valueOf(basket.get().getId()) : "not found";
		logger.debug("basket found with id: " + basketFoundMsg);
		return basket.isPresent() ? basket.get() : null;
	}
		
	public void saveTickets(Set<Ticket> tickets) {
		if (tickets != null && tickets.size() > 0) {
			logger.debug("Persisting a batch of " + tickets.size() + " tickets");
			basketRepository.saveAll((ArrayList)tickets.stream().collect(Collectors.toList()));
		}
	}
	
}

