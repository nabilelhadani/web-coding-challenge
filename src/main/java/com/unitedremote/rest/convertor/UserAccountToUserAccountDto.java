package com.unitedremote.rest.convertor;

import org.springframework.core.convert.converter.Converter;

import com.unitedremote.entities.model.UserAccount;
import com.unitedremote.rest.dto.UserAccountDto;

public class UserAccountToUserAccountDto implements Converter<UserAccount, UserAccountDto>{

	@Override
	public UserAccountDto convert(UserAccount user) {
		return UserAccountDto.builder()
				.userid(user.getUserid())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.password(user.getPassword())
				.isActive(user.isActive())
				.build();
	}

}
