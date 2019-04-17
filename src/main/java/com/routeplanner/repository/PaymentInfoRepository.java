package com.routeplanner.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.routeplanner.shopping.PaymentInfo;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Integer> {

	
}