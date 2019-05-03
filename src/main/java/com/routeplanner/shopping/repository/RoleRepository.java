package com.routeplanner.shopping.repository;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	@Query("SELECT b FROM Basket b join b.user u WHERE u.id = :userId and b.open is true")
	Optional<Basket> findOpenBasketForUser(@Param("userId") int userId);
	
	
	@Query("SELECT r FROM Role r WHERE r.role = 'MEMBER'")
	Optional<Role> getMembershipRole();
	
}


