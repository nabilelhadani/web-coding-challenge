package com.unitedremote.rest.endpoint;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.unitedremote.entities.model.ResponseMessage;
import com.unitedremote.entities.model.UserAccount;
import com.unitedremote.entities.repository.UserAccountRepository;
import com.unitedremote.rest.input.LoginForm;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Authentication {
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginInput) {
		
		UserAccount user = userAccountRepository.findByEmailIgnoreCase(loginInput.getEmail());
		if (user != null && user.isActive()) {
			return new ResponseEntity<>(new ResponseMessage("Login successful "), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(new ResponseMessage("Bad login "), HttpStatus.OK);

		}
		
	}

	

}
