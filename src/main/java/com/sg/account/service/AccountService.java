package com.sg.account.service;

import com.sg.account.core.BankingOperation;
import com.sg.account.core.BankingOpperationException;
import com.sg.account.dto.AccountDTO;
import com.sg.account.dto.BankingOperationDTO;
import com.sg.account.model.Account;
import com.sg.account.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Optional<AccountDTO> withdrawAmountOfMoney(BankingOperationDTO bankingOperationDTO){

        Optional<Account> account = accountRepository.findById(bankingOperationDTO.getIdAccount());
        if(account.isEmpty()) throw new BankingOpperationException("bank account was not found");

        BankingOperation operation = new BankingOperation(account.get());
        operation.withdrawAmountOfMoney(bankingOperationDTO.getAmount());
        accountRepository.save(operation.getAccount());
        return Optional.of( new AccountDTO(account.get()) );
    }

    @Transactional
    public Optional<AccountDTO> depositAmountOfMoney(BankingOperationDTO bankingOperationDTO){

        Optional<Account> account = accountRepository.findById(bankingOperationDTO.getIdAccount());
        if(account.isEmpty()) throw new BankingOpperationException("bank account was not found");

        BankingOperation operation = new BankingOperation(account.get());
        operation.depositAmountOfMoney(bankingOperationDTO.getAmount());
        accountRepository.save(operation.getAccount());
        return Optional.of( new AccountDTO(account.get()) );
    }

}
