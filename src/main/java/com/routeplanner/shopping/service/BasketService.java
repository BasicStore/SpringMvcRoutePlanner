package com.routeplanner.shopping.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.repository.BasketRepository;


@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED) 
@Service
public class BasketService {

	@Autowired
	private BasketRepository<Basket> basketRepository;
	
	public BasketService() {
		
	}
		
	public void save(Basket basket) {
		basketRepository.save(basket);
	}
	
	public Basket findOpenBasketForUser(int userId) {
		Optional<Basket> basket = basketRepository.findOpenBasketForUser(userId);
		return basket.isPresent() ? basket.get() : null;
	}

}

