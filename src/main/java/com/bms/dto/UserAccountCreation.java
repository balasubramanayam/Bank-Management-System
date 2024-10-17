package com.bms.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserAccountCreation {
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private String userPassword;
	private String userDOB;
	private String userMobile;
	private String userGender;
	private String accountNumber;
	private String address;
	private String ifscCode;
	private String branchName;
	private boolean freshUser;
	private LocalDate accountCreationDate;
}
