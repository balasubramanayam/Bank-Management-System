package com.bms.service;

import java.util.List;

import com.bms.dto.LoginRequest;
import com.bms.dto.UserAccountCreation;
import com.bms.model.User;



public interface UserService {

	
    public UserAccountCreation createUser(UserAccountCreation userAccountCreation); 
	
    public User login(LoginRequest loginRequest);
	
	public List<User> getAllUser();
	
	public int updateUser(User user);
	
	public User getById(int id);
	
	public void deleteUser(int id);
	
	public boolean updatePassword(int userId, String newPassword);
	
	  public void save(User user) ;
}
