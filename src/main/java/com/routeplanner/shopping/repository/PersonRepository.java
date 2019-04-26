package com.routeplanner.shopping.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.routeplanner.shopping.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	
}


