package com.routeplanner.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.routeplanner.shopping.Basket;

@Repository
public interface BasketRepository<T> extends JpaRepository<Basket, Integer> {
	
	@Query("SELECT b FROM Basket b join b.user u WHERE u.id = :userId and b.open is true")
	Basket findOpenBasketForUser(@Param("userId") int userId);

}



