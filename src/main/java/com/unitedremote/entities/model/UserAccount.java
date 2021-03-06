package com.unitedremote.entities.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_account")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder()
public class UserAccount implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	@NaturalId
	@Email
	private String email;

	@NotBlank
	@Size(min = 6)
	private String password;

	@Transient
	@NotBlank
	@Size(min = 6)
	private String confirmPassword;

	@Temporal(TemporalType.TIMESTAMP)
	@Builder.Default
	private Date createdDate = new Date();;

	private boolean isActive;

}
