package com.routeplanner.shopping.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.routeplanner.shopping.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	
}

