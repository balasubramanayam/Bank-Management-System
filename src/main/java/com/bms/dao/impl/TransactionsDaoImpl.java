package com.bms.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bms.dao.TransactionsDao;
import com.bms.model.Account;
import com.bms.model.Transactions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class TransactionsDaoImpl implements TransactionsDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void deposit(Account account, Transactions transactions) {
		// Validate input
		if (account == null || transactions == null || transactions.getAmount() == null) {
			throw new IllegalArgumentException("Account and transaction details must not be null.");
		}

		// Update account balance
		account.setBalance(account.getBalance() + transactions.getAmount());

		// Set transaction details
		transactions.setAccount(account);
		transactions.setTransactionDate(LocalDate.now());
		transactions.setType("Deposit");

		// Persist changes
		entityManager.merge(account); // Update account
		entityManager.persist(transactions); // Save transaction

	}

	@Transactional
	@Override
	public void withdraw(Account account, Transactions transactions) {
		// Validate input
		if (account == null || transactions == null || transactions.getAmount() == null) {
			throw new IllegalArgumentException("Account and transaction details must not be null.");
		}

		// Check if sufficient balance exists for withdrawal
		if (account.getBalance() < transactions.getAmount()) {
			throw new IllegalArgumentException("Insufficient balance for withdrawal.");
		}

		// Update account balance
		account.setBalance(account.getBalance() - transactions.getAmount());

		// Set transaction details
		transactions.setAccount(account);
		transactions.setTransactionDate(LocalDate.now());
		transactions.setType("Withdrawal");

		// Persist changes
		entityManager.merge(account);
		entityManager.persist(transactions);
	}

//	// Method to get all transactions
//	public List<Transactions> getAllTransactions() {
//		TypedQuery<Transactions> query = entityManager.createQuery("SELECT t FROM Transactions t", Transactions.class);
//		return query.getResultList();
//	}
//
//	// Method to get all deposits
//	public List<Transactions> getAllDeposits() {
//		TypedQuery<Transactions> query = entityManager
//				.createQuery("SELECT t FROM Transactions t WHERE t.type = 'Deposit'", Transactions.class);
//		return query.getResultList();
//	}
//
//    // Method to get all withdrawals
//	public List<Transactions> getAllWithdrawals() {
//		TypedQuery<Transactions> query = entityManager
//				.createQuery("SELECT t FROM Transactions t WHERE t.type = 'Withdrawal'", Transactions.class);
//		return query.getResultList();
//	}

	

	  @Override
	    public List<Transactions> getTransactionsByAccountId(Long accountId) {
	        String jpql = "SELECT t FROM Transactions t WHERE t.account.id = :accountId";
	        TypedQuery<Transactions> query = entityManager.createQuery(jpql, Transactions.class);
	        query.setParameter("accountId", accountId);
	        return query.getResultList();
	    }

}