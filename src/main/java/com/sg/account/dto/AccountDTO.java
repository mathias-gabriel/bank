package com.sg.account.dto;

import com.sg.account.model.Account;
import lombok.Data;

@Data
public class AccountDTO {

    private String accountId;
    private Double accountBalance;

    public AccountDTO(Account account){
        this.accountId = account.getId();
        this.accountBalance = account.getAccountBalance();
    }

}
