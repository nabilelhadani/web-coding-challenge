package com.unitedremote.entities.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitedremote.entities.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository <ConfirmationToken, Long>{

    ConfirmationToken findByConfirmationToken(String confirmationToken);

}
