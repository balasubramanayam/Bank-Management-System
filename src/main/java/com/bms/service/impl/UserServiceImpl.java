
package com.bms.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bms.dao.AccountDao;
import com.bms.dao.UserDao;
import com.bms.dto.LoginRequest;
import com.bms.dto.UserAccountCreation;
import com.bms.model.Account;
import com.bms.model.User;
import com.bms.service.UserService;
import com.bms.util.AccountUtil;
import com.bms.util.EmailUtil;
import jakarta.mail.MessagingException;


@Service
public class UserServiceImpl implements UserService {

	UserDao userdao;
	BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserDao userdao, BCryptPasswordEncoder passwordEncoder, AccountDao accountDao,
			AccountUtil accountUtil, EmailUtil emailUtil) {
		super();
		this.userdao = userdao;
		this.passwordEncoder = passwordEncoder;
		this.accountDao = accountDao;
		this.accountUtil = accountUtil;
		this.emailUtil = emailUtil;
	}

	AccountDao accountDao;

	private AccountUtil accountUtil;

	private EmailUtil emailUtil;

	@Override
	public UserAccountCreation createUser(UserAccountCreation userAccountCreation) {
		String accountNumber = accountUtil.generateAccountNumber();
		String randomPassword = accountUtil.generateRandomPassword();

		try {
			emailUtil.sendOtpEmail(userAccountCreation.getUserEmail(), accountNumber,
					userAccountCreation.getUserFirstName(), userAccountCreation.getUserLastName(), randomPassword,
					userAccountCreation.getUserMobile(), userAccountCreation.getUserGender());
		} catch (MessagingException e) {
			throw new RuntimeException("Failed to send account details email. Please try again.");
		}
		User user = new User();
		Account account = new Account();
		user.setUserFirstName(userAccountCreation.getUserFirstName());
		user.setUserLastName(userAccountCreation.getUserLastName());
		user.setUserEmail(userAccountCreation.getUserEmail());  
		user.setUserDOB(userAccountCreation.getUserDOB());
		user.setUserGender(userAccountCreation.getUserGender());
		user.setUserMobile(userAccountCreation.getUserMobile());
		user.setUserPassword(randomPassword);
		user.setAddress(userAccountCreation.getAddress());
		user.setFreshUser(true);   //only user can change the password
		account.setAccountCreationDate(userAccountCreation.getAccountCreationDate());
		account.setAccountNumber(accountNumber);
		account.setBalance(0.0);
		account.setUser(user);
		account.setIfscCode(userAccountCreation.getIfscCode());
		account.setBranchName(userAccountCreation.getBranchName());
		userAccountCreation.setAccountNumber(accountNumber);
		userAccountCreation.setAccountCreationDate(LocalDate.now());
		userAccountCreation.setUserPassword(randomPassword);
		userAccountCreation.setFreshUser(true);
		userdao.createUser(user);
		accountDao.accountDetail(account); 
		
		return userAccountCreation;   
	}

	public User login(LoginRequest loginRequest) {
	    User user = userdao.getEmail(loginRequest.getUserEmail());
	    return user; }


	@Override
	public List<User> getAllUser() {
		return userdao.getAllUser();
	}

	@Override
	public int updateUser(User user) {
		return userdao.updateUser(user);
	}

	@Override
	public User getById(int id) {
		return userdao.getById(id);
	}

	@Override
	public void deleteUser(int userId) {
		userdao.deleteUser(userId); 

	}

	public boolean updatePassword(int userId, String newPassword) {
		int updatedRows = userdao.updatePassword(userId, newPassword);
		return updatedRows > 0; 
	}

	@Override
	public void save(User user) {
		userdao.save(user);
	}

}
