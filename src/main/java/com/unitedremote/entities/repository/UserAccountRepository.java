package com.unitedremote.entities.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitedremote.entities.model.UserAccount;

public interface UserAccountRepository extends JpaRepository <UserAccount, Long> {
	
	UserAccount findByEmailIgnoreCase(String email);
	Optional<UserAccount> findByEmail(String email);
    Boolean existsByEmail(String email);


	

}


