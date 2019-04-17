package com.routeplanner.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.routeplanner.shopping.Basket;

public interface BasketRepository extends JpaRepository<Basket, Integer> {

	
}

