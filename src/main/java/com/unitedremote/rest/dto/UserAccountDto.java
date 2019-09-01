package com.unitedremote.rest.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccountDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private long userid;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean isActive;

}
