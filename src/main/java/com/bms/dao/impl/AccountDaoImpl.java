package com.bms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bms.dao.AccountDao;
import com.bms.model.Account;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AccountDaoImpl implements AccountDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public Account accountDetail(Account account) {
		entityManager.persist(account);
		return account;
	}

	@Override
	public List<Account> getAllAccounts() {
		TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a", Account.class);
		return query.getResultList();
	}

	@Override
	public Account getByAccountId(int id) {
		TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a WHERE a.id = :id",
				Account.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult(); // Return the Account object
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional
	@Override
	public Account UpdateAccount(Account account) {

		String updateAccountQuery = "UPDATE Account a SET a.accountNumber = :accountNumber, "
				+ "a.balance = :balance, a.ifscCode = :ifscCode, a.branchName = :branchName "
				+ "WHERE a.id = :accountId";

		int updatedCount = entityManager.createQuery(updateAccountQuery)
				.setParameter("accountNumber", account.getAccountNumber()).setParameter("balance", account.getBalance())
				.setParameter("ifscCode", account.getIfscCode()).setParameter("branchName", account.getBranchName())
				.setParameter("accountId", account.getId()).executeUpdate();

		if (updatedCount > 0 && account.getUser() != null) {
			String updateUserQuery = "UPDATE User u SET u.userFirstName = :firstName, "
					+ "u.userLastName = :lastName, u.userEmail = :email,u.userDOB=:userDOB,u.userMobile=:userMobile,"
					+ "u.userGender=:userGender,u.address=:address  WHERE u.userId = :userId";

			entityManager.createQuery(updateUserQuery).setParameter("firstName", account.getUser().getUserFirstName())
					.setParameter("lastName", account.getUser().getUserLastName())
					.setParameter("email", account.getUser().getUserEmail())
					.setParameter("userDOB", account.getUser().getUserDOB())
					.setParameter("userMobile", account.getUser().getUserMobile())
					.setParameter("userGender", account.getUser().getUserGender())
					.setParameter("address", account.getUser().getAddress())
					.setParameter("userId", account.getUser().getUserId()).executeUpdate();
		}

		return account;
	}

}
