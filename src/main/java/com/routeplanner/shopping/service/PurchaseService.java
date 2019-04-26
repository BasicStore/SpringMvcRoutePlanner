package com.routeplanner.shopping.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.routeplanner.shopping.Purchase;
import com.routeplanner.shopping.repository.PurchaseRepository;


@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED)
@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;
	
	public PurchaseService() {
		
	}

	public void save(Purchase purchase) {
		purchaseRepository.save(purchase);
	}
	
}

