package com.sg.account.core;

import com.sg.account.model.Account;
import lombok.Getter;

@Getter
public class BankingOperation {

    private Account account;

    public BankingOperation(Account account) {
        this.account = account;
    }

    public void withdrawAmountOfMoney(Double amountOfMoney){

        Double newAmmount = accountBalanceAfterWithdrawByAccount(amountOfMoney);
        account.setAccountBalance(newAmmount);
    }

    public void depositAmountOfMoney(Double amountOfMoney){

        Double newAmmount = accountBalanceAfterDepositeByAccount(amountOfMoney);
        account.setAccountBalance(newAmmount);
    }

    private Double accountBalanceAfterWithdrawByAccount(Double amountOfMoney){
        return account.getAccountBalance() - amountOfMoney;
    }

    private Double accountBalanceAfterDepositeByAccount(Double amountOfMoney){
        return account.getAccountBalance() + amountOfMoney;
    }
}