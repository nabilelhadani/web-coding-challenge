package com.unitedremote.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unitedremote.entities.model.UserAccount;
import com.unitedremote.entities.repository.UserAccountRepository;
import com.unitedremote.rest.input.UserPrinciple;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserAccountRepository userrepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserAccount user = userrepo.findByEmail(email).orElseThrow(
		        () -> new UsernameNotFoundException("User Not Found with -> this email : " + email));
	    return UserPrinciple.build(user);
	}
}
