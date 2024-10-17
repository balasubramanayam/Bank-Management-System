package com.bms.dto;


import com.bms.model.Account;
import com.bms.model.Transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DepositeRequest {

	 private Account account;
     private Transactions transactions;
}
