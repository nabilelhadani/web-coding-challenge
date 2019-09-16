package com.unitedremote.rest.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.unitedremote.entities.model.ConfirmationToken;
import com.unitedremote.rest.dto.ConfirmationTokenDto;

@Component
public class ConfirmationTokenToConfirmationTokenDto implements Converter<ConfirmationToken, ConfirmationTokenDto>{
	
	@Nullable
	@Override
	public ConfirmationTokenDto convert(ConfirmationToken confirm) {
		return ConfirmationTokenDto.builder()
				.tokenid(confirm.getTokenid())
				.confirmationToken(confirm.getConfirmationToken())
				.createdDate(confirm.getCreatedDate())
				.user(confirm.getUser())
				.build();
	}
}
