package com.sg.account.core;

import com.sg.account.model.Account;
import com.sg.account.model.Currency;
import com.sg.account.model.TransferCommand;
import lombok.Getter;

import java.time.OffsetDateTime;

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

    public TransferCommand transfert(Account toAccount, Double amountOfMoney, Currency currency){
        withdrawAmountOfMoney(amountOfMoney);
        Double newBalance = toAccount.getAccountBalance() + amountOfMoney;
        toAccount.setAccountBalance(newBalance);

        TransferCommand transfer = TransferCommand.builder()
                .fromAccount(this.getAccount())
                .toAccount(toAccount)
                .balance(amountOfMoney)
                .date(OffsetDateTime.now())
                .currency(currency)
                .build();

        return transfer;
    }

    private Double accountBalanceAfterWithdrawByAccount(Double amountOfMoney){
        return account.getAccountBalance() - amountOfMoney;
    }

    private Double accountBalanceAfterDepositeByAccount(Double amountOfMoney){
        return account.getAccountBalance() + amountOfMoney;
    }
}
