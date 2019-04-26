package com.routeplanner.shopping.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.routeplanner.shopping.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

	
}

