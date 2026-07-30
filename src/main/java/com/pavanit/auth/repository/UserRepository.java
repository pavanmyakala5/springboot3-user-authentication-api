package com.pavanit.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavanit.auth.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{		
	
	   Optional<User> findByEmail(String email);

	    Optional<User> findByMobile(String mobile);

	    boolean existsByEmail(String email);

	    boolean existsByMobile(String mobile);

}
