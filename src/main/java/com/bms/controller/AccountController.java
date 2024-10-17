package com.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.model.Account;
import com.bms.service.AccountService;
@CrossOrigin("http://localhost:3000/")
@RestController
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@GetMapping("/getAllAccounts")
	public ResponseEntity<List<Account>> getAllAccounts() {
	    List<Account> accountList = accountService.getAllAccounts();
	    return ResponseEntity.ok(accountList);
	}
    
	
	@GetMapping("/accountId/{id}")
	public ResponseEntity<Account> getByAccount(@PathVariable int id) {
	    Account account = accountService.getByAccountId(id);
	    if (account != null) {
	        return ResponseEntity.ok(account);
	    } 
	    else {
	        return ResponseEntity.notFound().build(); 
	    }
	}
	
	
	@PutMapping("/updateAccount/{id}")
	public ResponseEntity<Account> updateUser(@PathVariable int id, @RequestBody Account account) {
		account.setId(id);
		Account result = accountService.updateAccount(account); 
		return ResponseEntity.ok(result);
	}
	
	
}
