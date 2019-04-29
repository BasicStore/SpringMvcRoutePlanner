package com.routeplanner.shopping.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.repository.RouteQueryRepository;


@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED)
@Service
public class RouteQueryService {

	@Autowired
	private RouteQueryRepository routeQueryRepository;
	
	public RouteQueryService() {
		
	}

	public void save(RouteQuery routeQuery) {
		routeQueryRepository.save(routeQuery);
	}
	
}

