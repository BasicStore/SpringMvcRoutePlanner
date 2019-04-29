package com.routeplanner.shopping.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.routeplanner.shopping.RouteQuery;

public interface RouteQueryRepository<T> extends JpaRepository<RouteQuery, Integer> {

	
}
