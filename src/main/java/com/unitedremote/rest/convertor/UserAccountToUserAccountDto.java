package com.unitedremote.rest.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.unitedremote.entities.model.UserAccount;
import com.unitedremote.rest.dto.UserAccountDto;

@Component
public class UserAccountToUserAccountDto implements Converter<UserAccount, UserAccountDto>{

	@Nullable
	@Override
	public UserAccountDto convert(UserAccount user) {
		return UserAccountDto.builder()
				.userId(user.getUserId())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.password(user.getPassword())
				.isActive(user.isActive())
				.build();
	}

}
