package com.bms.dao;

import java.util.List;

import com.bms.model.Account;

public interface AccountDao {

	 public Account accountDetail(Account account);
	 
	 public List<Account> getAllAccounts();
	 
	 public Account getByAccountId(int id);
	 
	 public Account UpdateAccount(Account account);
	 
	 
}
