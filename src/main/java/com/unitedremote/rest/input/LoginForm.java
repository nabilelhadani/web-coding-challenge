package com.unitedremote.rest.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginForm {
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 6)
	private String password;

}
