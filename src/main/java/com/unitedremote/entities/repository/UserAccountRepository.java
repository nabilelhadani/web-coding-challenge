package com.unitedremote.entities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitedremote.entities.model.UserAccount;

public interface UserAccountRepository extends JpaRepository <UserAccount, Long> {
	
	UserAccount findByEmailIgnoreCase(String email);

}


