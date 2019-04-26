package com.routeplanner.shopping.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.routeplanner.shopping.Ticket;
import com.routeplanner.shopping.repository.TicketRepository;

@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED)
@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	public TicketService() {
	
	}

	public void save(Ticket ticket) {
		ticketRepository.save(ticket);
	}
	
}

