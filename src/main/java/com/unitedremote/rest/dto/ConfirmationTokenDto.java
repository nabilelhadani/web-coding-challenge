package com.unitedremote.rest.dto;

import java.io.Serializable;
import java.util.Date;
import com.unitedremote.entities.model.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmationTokenDto implements Serializable {
	private static final long serialVersionUID = 1L;
    private long tokenid;
    private String confirmationToken;
    private Date createdDate;
    private UserAccount user;

}
