/**
 * 
 */
package com.unitedremote.rest.input;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unitedremote.entities.model.UserAccount;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author nabil
 *
 */

@NoArgsConstructor
@AllArgsConstructor
public class UserPrinciple implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private long userId;

	private String firstName;

	private String lastName;

	private String email;

    @JsonIgnore
	private String password;
    
    
    public static UserPrinciple build(UserAccount user) {
    	return new UserPrinciple(
    			user.getUserId(),
    			user.getFirstName(),
    			user.getLastName(),
    			user.getEmail(),
                user.getPassword()
        );
    }

	public long getUserId() {
		return userId;
	}



	public String getFirstName() {
		return firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public String getEmail() {
		return email;
	}
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(userId, user.userId);
    }


}
