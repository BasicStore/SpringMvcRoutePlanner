package com.routeplanner.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.routeplanner.shopping.PassengerType;

public interface PassengerTypeRepository extends JpaRepository<PassengerType, Integer> {

	
}