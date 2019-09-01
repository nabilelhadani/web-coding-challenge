package com.unitedremote.rest.endpoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unitedremote.entities.model.ConfirmationToken;
import com.unitedremote.entities.model.MailRequest;
import com.unitedremote.entities.model.ResponseMessage;
import com.unitedremote.entities.model.UserAccount;
import com.unitedremote.entities.repository.ConfirmationTokenRepository;
import com.unitedremote.entities.repository.UserAccountRepository;
import com.unitedremote.rest.dto.UserAccountDto;
import com.unitedremote.rest.exception.ResourceNotFoundException;
import com.unitedremote.rest.input.UserAccountInput;
import com.unitedremote.services.EmailService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserAccountController {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ConversionService conversionService;

	@GetMapping("/users")
	@Transactional(readOnly = true)
	public ResponseEntity<?> getAllUsers() {
		List<UserAccount> users = userAccountRepository.findAll();
		if (users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users.stream().map(user -> conversionService.convert(user, UserAccountDto.class))
				.collect(Collectors.toList()));
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserAccountInput input) throws ResourceNotFoundException{

		UserAccount existingUser = userAccountRepository.findByEmailIgnoreCase(input.getEmail());
		if (existingUser != null) {
			return new ResponseEntity<>(new ResponseMessage("This email already exists!"), HttpStatus.BAD_REQUEST);
		} else {
			UserAccount user = userAccountRepository.save(buildUserAccount(UserAccount.builder().build(), input));
			ConfirmationToken confirmationToken = new ConfirmationToken(user);
			confirmationTokenRepository.save(confirmationToken);
			MailRequest request = new MailRequest();
			request.setTo(input.getEmail());
			request.setSubject("Complete Registration!");
			request.setFrom("nabil.elhadani1991@gmail.com");
			Map<String, Object> model = new HashMap<>();
			model.put("confirmationToken", confirmationToken);
			emailService.sendEmail(request, model);
			return new ResponseEntity<>(new ResponseMessage("Successful registeration"), HttpStatus.OK);
		}

	}

	@GetMapping("/users/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<?> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {

		UserAccount user = userAccountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

		if (user == null) {

			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(conversionService.convert(user, UserAccountDto.class));
	}

	@GetMapping("/confirm-account")
	public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		if (token != null) {
			UserAccount user = userAccountRepository.findByEmailIgnoreCase(token.getUser().getEmail());
			user.setActive(true);
			userAccountRepository.save(user);
			return new ResponseEntity<>(new ResponseMessage("account verified"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseMessage("The link is invalid or broken!"), HttpStatus.BAD_REQUEST);
		}

	}
	
	
	public UserAccount buildUserAccount(UserAccount user, UserAccountInput input) throws ResourceNotFoundException {
		//user.setUserid(input.getUserid());
		user.setFirstName(input.getFirstName());
		user.setLastName(input.getLastName());
		user.setEmail(input.getEmail());
		user.setPassword(input.getPassword());
		user.setCreatedDate(input.getCreatedDate());
		return user;
	}

}
