package com.bms.dao;





import java.util.List;

import com.bms.model.Account;
import com.bms.model.Transactions;



public interface TransactionsDao {

	 
	 public void deposit(Account account, Transactions transactions);
	 public void withdraw(Account account, Transactions transactions);
//	 public List<Transactions> getAllTransactions();
//	 public List<Transactions> getAllDeposits();
//	 public List<Transactions> getAllWithdrawals();
	  
	 public List<Transactions> getTransactionsByAccountId(Long accountId) ;
	       
}
