package com.unitedremote.entities.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MailRequest {
	private String to;
	private String from;
	private String subject;

}
