package com.unitedremote.rest.endpoint;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unitedremote.rest.ApiConstants;
import com.unitedremote.rest.input.LoginForm;
import com.unitedremote.security.JwtProvider;
import com.unitedremote.security.JwtResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(ApiConstants.BASE_API)
@CrossOrigin
public class AuthRestAPIs  {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtProvider jwtProvider;

	@ApiOperation(value = "Method to sign in a user ")
	@ApiResponses({ @ApiResponse(code = HttpServletResponse.SC_OK, message = "Login successful"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Bad login") })
	@PostMapping(ApiConstants.SIGNIN_USERS)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginInput) {
	    Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(loginInput.getEmail(), loginInput.getPassword()));
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtProvider.generateJwtToken(authentication);
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername()));

	}
}
