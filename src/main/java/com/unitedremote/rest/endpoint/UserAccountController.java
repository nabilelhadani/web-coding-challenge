package com.unitedremote.rest.endpoint;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.unitedremote.rest.ApiConstants;
import com.unitedremote.rest.dto.UserAccountDto;
import com.unitedremote.rest.exception.ResourceNotFoundException;
import com.unitedremote.rest.input.SignUpForm;
import com.unitedremote.services.EmailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@CrossOrigin
@RestController
@RequestMapping(ApiConstants.BASE_API)
@Api(value = ApiConstants.BASE_API, tags = { "Swagger Resource" })
@SwaggerDefinition(tags = { @Tag(name = "Swagger Resource", description = "User Controller") })
public class UserAccountController {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private ConversionService conversionService;

	private static final String USER_ID = "userId";

	@ApiOperation(value = "Method to get all users")
	@ApiResponses({
			@ApiResponse(code = HttpServletResponse.SC_OK, message = "List of users", response = UserAccountDto.class, responseContainer = "List") })
	@GetMapping(ApiConstants.ALL_USERS)
	@Transactional(readOnly = true)
	public ResponseEntity<?> getAllUsers() {
		List<UserAccount> users = userAccountRepository.findAll();
		if (users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users.stream().map(user -> conversionService.convert(user, UserAccountDto.class))
				.collect(Collectors.toList()));
	}

	@ApiOperation(value = "Method to sign up a user ")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "Successful registerationr"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad registeration") })
	@PostMapping(ApiConstants.SIGNUP_USERS)
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm input) throws ResourceNotFoundException {

		if (userAccountRepository.existsByEmail(input.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
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
			// emailService.sendEmail(request, model);
			return new ResponseEntity<>(new ResponseMessage("Successful registeration"), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Method to get a user ")
	@ApiResponses({
			@ApiResponse(code = HttpServletResponse.SC_OK, message = "Get user", response = UserAccountDto.class) })
	@GetMapping(ApiConstants.USER + "/{" + USER_ID + "}")
	@Transactional(readOnly = true)
	public ResponseEntity<?> getUserById(@PathVariable(value = USER_ID) Long id) throws ResourceNotFoundException {

		UserAccount user = userAccountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
		System.out.println(user);

		if (user == null) {

			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(conversionService.convert(user, UserAccountDto.class));
	}

	@ApiOperation(value = "Method to confirm user account ")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "account verified"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "The link is invalid or broken!") })
	@GetMapping(ApiConstants.CONFIRM_ACCOUNT)
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

	public UserAccount buildUserAccount(UserAccount user, SignUpForm input) throws ResourceNotFoundException {
		user.setFirstName(input.getFirstName());
		user.setLastName(input.getLastName());
		user.setEmail(input.getEmail());
		user.setPassword(encoder.encode(input.getPassword()));
		user.setConfirmPassword(encoder.encode(input.getConfirmPassword()));
		return user;
	}

}
