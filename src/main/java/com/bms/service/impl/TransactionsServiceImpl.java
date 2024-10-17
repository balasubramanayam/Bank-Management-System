package com.bms.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.dao.TransactionsDao;
import com.bms.model.Account;
import com.bms.model.Transactions;
import com.bms.service.TransactionsService;



@Service
public class TransactionsServiceImpl implements TransactionsService {

	
	    @Autowired
	    TransactionsDao transactionsDao;

	    public void deposit(Account account, Transactions transactions) {
	        transactionsDao.deposit(account, transactions); 
	    }

		@Override
		public void withdraw(Account account, Transactions transactions) {
			 transactionsDao.withdraw(account, transactions);
		}
	    
		
//		@Override
//	    public List<Transactions> getAllTransactions() {
//	        return transactionsDao.getAllTransactions();
//	    }
//
//		@Override
//	    public List<Transactions> getAllDeposits() {
//	        return transactionsDao.getAllDeposits();
//	    }
//
//		@Override
//	    public List<Transactions> getAllWithdrawals() {
//	        return transactionsDao.getAllWithdrawals();
//	    }
	    
		@Override
	    public List<Transactions> getTransactionsByAccountId(Long accountId){
			return transactionsDao.getTransactionsByAccountId(accountId) ;
	    	
	    }
}
