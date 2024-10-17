package com.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.dao.UserDao;
import com.bms.dto.LoginRequest;
import com.bms.dto.UserAccountCreation;
import com.bms.model.User;
import com.bms.service.UserService;
@CrossOrigin("http://localhost:3000/")
@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserDao userdao;

	@PostMapping("/createAccount")
	public ResponseEntity<UserAccountCreation> createUser(@RequestBody UserAccountCreation userAccountCreation) {
		try {
			UserAccountCreation createdUserAccount = userService.createUser(userAccountCreation);
			return new ResponseEntity<>(createdUserAccount, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	 @PostMapping("/login")
	    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
	        User user = userService.login(loginRequest);
	            return ResponseEntity.ok( user);
	       
	  }
	 
	 @PostMapping("/updateUserStatus")
	 public ResponseEntity<String> updateUserStatus(@RequestBody User userRequest) {
	     User user = userService.getById(userRequest.getUserId());
	     if (user != null) {
	         user.setFreshUser(false);
	         userService.save(user);
	         return ResponseEntity.ok("User status updated successfully.");
	     }
	     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
	 }




	@PutMapping("/updatePassword/{userId}")
	public ResponseEntity<String> updatePassword(@PathVariable int userId, @RequestBody String newPassword) {
		boolean isUpdated = userService.updatePassword(userId, newPassword);

		if (isUpdated) {
			return ResponseEntity.ok("Password updated successfully.");
		} else {
			return ResponseEntity.status(404).body("User not found or password not updated.");
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> list = userService.getAllUser();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody User user) {
		user.setUserId(userId);
		int result = userService.updateUser(user);  
		return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

	@GetMapping("/getById/{userId}")
	public ResponseEntity<User> getByUserId(@PathVariable int userId) {
		User updatedUser = userService.getById(userId);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/deleteByUser/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
	    userService.deleteUser(userId);
	    return ResponseEntity.noContent().build();
	}

}
