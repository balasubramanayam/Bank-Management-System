package com.bms.dao.impl;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.bms.dao.UserDao;
import com.bms.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public User createUser(User user) {
		entityManager.persist(user);
		return user;
	}
	
	
	@Transactional
	@Override
	public User getEmail(String email) {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.userEmail = :email",
				User.class);
		query.setParameter("email", email);
		User user = query.getResultStream().findFirst().orElse(null);
		return user;
	}
	
	
	@Override
	public List<User> getAllUser() {
		TypedQuery<User> query = entityManager.createQuery("From User", User.class);
		return query.getResultList();
	}

	@Transactional
	@Override
	public int updateUser(User user) {
	    try {
	        Query query = entityManager.createQuery("UPDATE User u SET u.userEmail = :userEmail, "
	                + "u.userFirstName = :userFirstName, u.userLastName = :userLastName, "
	                + "u.userDOB = :userDOB, u.userMobile = :userMobile, "
	                + "u.userImageData = :userImageData WHERE u.userId = :userId");

	        query.setParameter("userFirstName", user.getUserFirstName());
	        query.setParameter("userLastName", user.getUserLastName());
	        query.setParameter("userDOB", user.getUserDOB());
	        query.setParameter("userMobile", user.getUserMobile());
	        query.setParameter("userImageData", user.getUserImageData());
	        query.setParameter("userEmail", user.getUserEmail());
	        query.setParameter("userId", user.getUserId());

	        int rowsUpdated = query.executeUpdate();
	        entityManager.flush(); 
	        return rowsUpdated;

	    } catch (Exception e) {
	        System.err.println("Error updating user: " + e.getMessage());
	        return 0; 
	    }
	}


	@Override
	public User getById(int userId) {
		try {
			Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.userId = :userId");
			query.setParameter("userId", userId);
			return (User) query.getSingleResult();
		} catch (NoResultException e) {
            return null;
		}
	}


	
	@Transactional
	public void deleteUser(int userId) {
	    // First delete any related transactions
	    Query deleteTransactionsQuery = entityManager.createQuery("DELETE FROM Transactions t WHERE t.account.id = :id");
	    deleteTransactionsQuery.setParameter("id", userId);
	    deleteTransactionsQuery.executeUpdate();

	    // Then delete any related accounts
	    Query deleteAccountsQuery = entityManager.createQuery("DELETE FROM Account a WHERE a.id = :id");
	    deleteAccountsQuery.setParameter("id", userId);
	    deleteAccountsQuery.executeUpdate();

	    // Finally, delete the user
	    Query deleteUserQuery = entityManager.createQuery("DELETE FROM User u WHERE u.userId = :userId");
	    deleteUserQuery.setParameter("userId", userId);
	    deleteUserQuery.executeUpdate();
	}




	public String hashPassword(String plainPassword) {
		return BCrypt.hashpw(plainPassword, BCrypt.gensalt(10));
	}

	@Transactional
	@Override
	public int updatePassword(int userId, String userPassword) {
		String hashedPassword = hashPassword(userPassword);
		Query query = entityManager
				.createQuery("UPDATE User u SET u.userPassword = :newPassword WHERE u.userId = :userId");
		query.setParameter("newPassword", hashedPassword);
		query.setParameter("userId", userId);
		int update = query.executeUpdate();
		return update;
	}
	
	 @Transactional 
	    public void save(User user) {
	        entityManager.merge(user); 
	    }

}
