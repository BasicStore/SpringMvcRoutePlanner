package com.routeplanner.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.routeplanner.shopping.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	
}
