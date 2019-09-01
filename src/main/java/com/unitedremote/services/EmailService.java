package com.unitedremote.services;

import java.util.Map;

import com.unitedremote.entities.model.MailRequest;
import com.unitedremote.entities.model.MailResponse;

public interface EmailService {
	public MailResponse sendEmail(MailRequest request, Map<String, Object> model);

}
