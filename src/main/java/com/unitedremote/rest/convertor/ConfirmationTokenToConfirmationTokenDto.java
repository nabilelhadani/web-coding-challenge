package com.unitedremote.rest.convertor;

import org.springframework.core.convert.converter.Converter;
import com.unitedremote.entities.model.ConfirmationToken;
import com.unitedremote.rest.dto.ConfirmationTokenDto;

public class ConfirmationTokenToConfirmationTokenDto implements Converter<ConfirmationToken, ConfirmationTokenDto>{
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
