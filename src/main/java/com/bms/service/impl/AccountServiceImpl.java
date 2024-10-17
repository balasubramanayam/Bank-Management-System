package com.bms.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.dao.AccountDao;
import com.bms.model.Account;
import com.bms.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao accountdao;
	
	@Override
	public List<Account> getAllAccounts() {
		return accountdao.getAllAccounts();
	}

	@Override
	public Account getByAccountId(int id) {
		return  accountdao.getByAccountId(id);
	}

	@Override
	public Account updateAccount(Account account) {
		accountdao.UpdateAccount(account);
		return account;
	}

}
