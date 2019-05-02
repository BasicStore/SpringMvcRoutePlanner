package com.routeplanner.shopping.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(TicketService.class);
	
	@Autowired
	private TicketRepository<Ticket> ticketRepository;
	
	public TicketService() {
	
	}

	public void save(Ticket ticket) {
		ticketRepository.save(ticket);
		logger.debug("Ticket saved with id: " + ticket.getId());
	}

	
	public void delete(int ticketId) {
		ticketRepository.deleteById(ticketId);
		logger.debug("Ticket deleted with id: " + ticketId);
	}
	
}

