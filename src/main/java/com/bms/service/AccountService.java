package com.bms.service;

import java.util.List;

import com.bms.model.Account;

public interface AccountService {

	
	 public List<Account> getAllAccounts();
	 
	 public Account getByAccountId(int id);
	 
	 public Account updateAccount(Account account);
}
