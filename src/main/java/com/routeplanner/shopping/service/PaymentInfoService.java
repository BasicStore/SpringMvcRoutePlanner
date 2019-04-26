package com.routeplanner.shopping.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.routeplanner.shopping.ContactDetails;
import com.routeplanner.shopping.PaymentInfo;
import com.routeplanner.shopping.repository.ContractDetailsRepository;
import com.routeplanner.shopping.repository.PaymentInfoRepository;


@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED)
@Service
public class PaymentInfoService {

	@Autowired
	private PaymentInfoRepository paymentInfoRepository;
	
	@Autowired
	private ContractDetailsRepository contactDetailsRepository;
	
	public PaymentInfoService() {
		
	}
	
	public void save(PaymentInfo paymentInfo) {
		contactDetailsRepository.save(paymentInfo.getContactDetails());
		paymentInfoRepository.save(paymentInfo);
	}
	
	public void saveContactDetails(ContactDetails contactDetails) {
		contactDetailsRepository.save(contactDetails);
	}
	
}

