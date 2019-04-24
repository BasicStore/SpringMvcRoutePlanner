package com.routeplanner.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.routeplanner.shopping.Basket;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
	
	@Query("select b from basket b inner join fetch b.user where u.id = :userId and b.open = true")
	Basket findOpenBasketForUser(@Param("userId") int userId);
	
}

