package com.routeplanner.shopping.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.routeplanner.shopping.ContactDetails;

public interface RegistrationDetailsRepository<RegistrationDetails> extends JpaRepository<ContactDetails, Integer> {
	
	
}

