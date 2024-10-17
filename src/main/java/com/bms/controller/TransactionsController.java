package com.bms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.service.TransactionsService;
import com.bms.model.Account;
import com.bms.model.Transactions;
import com.bms.service.AccountService;

@CrossOrigin("http://localhost:3000/")
@RestController
public class TransactionsController {

	@Autowired
	private TransactionsService transactionsService;

	@Autowired
	AccountService accountService;

	@PostMapping("/deposit/{id}")
	public ResponseEntity<String> deposit(@PathVariable int id, @RequestBody Transactions transactions) {
		Account account = accountService.getByAccountId(id);

		if (account == null) {
			return ResponseEntity.notFound().build(); // Return 404 if the account doesn't exist
		}

		try {
			transactionsService.deposit(account, transactions);
			return ResponseEntity.ok("Deposit successful:" + transactions.getAmount());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("An error occurred during the deposit.");
		}
	}

	@PostMapping("/withdraw/{id}")
	public ResponseEntity<String> withdraw(@PathVariable int id, @RequestBody Transactions transactions) {
		Account account = accountService.getByAccountId(id);

		if (account == null) {
			return ResponseEntity.notFound().build();
		}

		try {
			transactionsService.withdraw(account, transactions);
			return ResponseEntity.ok("Withdraw successful: " + transactions.getAmount());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("An error occurred during the deposit.");
		}
	}

//	@GetMapping("/getalltransactions")
//	public ResponseEntity<List<Transactions>> getAllTransactions() {
//		List<Transactions> transactions = transactionsService.getAllTransactions();
//		return ResponseEntity.ok(transactions);
//	}
//
//	@GetMapping("/deposits")
//	public ResponseEntity<List<Transactions>> getAllDeposits() {
//		List<Transactions> deposits = transactionsService.getAllDeposits();
//		return ResponseEntity.ok(deposits);
//	}
//
//	@GetMapping("/withdrawals")
//	public ResponseEntity<List<Transactions>> getAllWithdrawals() {
//		List<Transactions> withdrawals = transactionsService.getAllWithdrawals();
//		return ResponseEntity.ok(withdrawals);
//	}

	@GetMapping("/transactionsById/{accountId}")
	public ResponseEntity<List<Transactions>> getTransactionsByAccountId(@PathVariable Long accountId) {
		List<Transactions> transactions = transactionsService.getTransactionsByAccountId(accountId);
		return ResponseEntity.ok(transactions);
	}

}
