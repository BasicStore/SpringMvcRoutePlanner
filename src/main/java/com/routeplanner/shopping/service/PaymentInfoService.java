package com.routeplanner.shopping.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(PaymentInfoService.class);
	
	@Autowired
	private PaymentInfoRepository paymentInfoRepository;
	
	@Autowired
	private ContractDetailsRepository contactDetailsRepository;
	
	public PaymentInfoService() {
		
	}
	
	public void save(PaymentInfo paymentInfo) {
		paymentInfoRepository.save(paymentInfo);
		logger.debug("Payment info saved with id: " + paymentInfo.getId());
	}
	
	public void saveContactDetails(ContactDetails contactDetails) {
		contactDetailsRepository.save(contactDetails);
		logger.debug("Contact details info saved with id: " + contactDetails.getId());
	}
	
}

