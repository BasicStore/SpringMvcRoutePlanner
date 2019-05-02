package com.routeplanner.shopping.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(RouteQueryService.class);
	
	@Autowired
	private RouteQueryRepository<RouteQuery> routeQueryRepository;
	
	public RouteQueryService() {
		
	}

	public void save(RouteQuery routeQuery) {
		routeQueryRepository.save(routeQuery);
		logger.debug("Route query saved with id: " + routeQuery.getId());
	}
	
}

