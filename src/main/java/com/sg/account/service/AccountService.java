package com.sg.account.service;

import com.sg.account.core.BankingOperation;
import com.sg.account.core.BankingOpperationException;
import com.sg.account.dto.AccountDTO;
import com.sg.account.dto.BankingOperationDTO;
import com.sg.account.dto.TransfertOperationDTO;
import com.sg.account.model.Account;
import com.sg.account.model.TransferCommand;
import com.sg.account.repositories.AccountRepository;
import com.sg.account.repositories.TransferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class AccountService {

    private AccountRepository accountRepository;

    private TransferRepository transferRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
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

    @Transactional
    public Optional<AccountDTO> transfert(TransfertOperationDTO transfertOperationDTO){

        Optional<Account> fromAccount = accountRepository.findById(transfertOperationDTO.getFromtIdAccount());
        if(fromAccount.isEmpty()) throw new BankingOpperationException("issuing bank account was not found");

        Optional<Account> toAccount = accountRepository.findById(transfertOperationDTO.getToIdAccount());
        if(toAccount.isEmpty()) throw new BankingOpperationException("bank account was not found");

        BankingOperation operation = new BankingOperation(fromAccount.get());
        TransferCommand transfer = operation.transfert(toAccount.get(), transfertOperationDTO.getAmount(), transfertOperationDTO.getCurrency());
        transferRepository.save(transfer);

        return Optional.of( new AccountDTO(transfer.getFromAccount()) );
    }
}
