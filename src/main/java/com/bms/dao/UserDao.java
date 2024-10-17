package com.bms.dao;

import java.util.List;

import com.bms.model.User;


public interface UserDao {
	
    public User createUser(User user);
	
	public User getEmail(String email);
	
	public List<User> getAllUser();
	
	public int updateUser(User user);
	
	public User getById(int userId);
	
	public void deleteUser(int userId);
	
	
	public void save(User user) ;
	
	public int updatePassword(int userId,String userPassword);
}
