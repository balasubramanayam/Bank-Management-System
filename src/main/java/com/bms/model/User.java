package com.bms.model;



import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Setter  
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="user_tbl")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private String userPassword;
	private String userDOB;	
	private String userMobile;
	private String userGender;
	private boolean active; 
	private String address;
	private String otp;
	private boolean freshUser; 
	private LocalDateTime otpGeneratedTime;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] userImageData;
}
