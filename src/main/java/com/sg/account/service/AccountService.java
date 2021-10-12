package com.sg.account.service;

import com.sg.account.core.BankingOperation;
import com.sg.account.core.BankingOpperationException;
import com.sg.account.dto.AccountDTO;
import com.sg.account.dto.BankingOperationDTO;
import com.sg.account.dto.TransferOperationDTO;
import com.sg.account.dto.TransferQueryDTO;
import com.sg.account.model.Account;
import com.sg.account.model.Currency;
import com.sg.account.model.TransferCommand;
import com.sg.account.repositories.AccountRepository;
import com.sg.account.repositories.TransferCommandRepository;
import com.sg.account.repositories.TransferReadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountService {

    private AccountRepository accountRepository;

    private TransferCommandRepository transferCommandRepository;

    private TransferReadRepository transferReadRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          TransferCommandRepository transferCommandRepository,
                          TransferReadRepository transferReadRepository) {
        this.accountRepository = accountRepository;
        this.transferCommandRepository = transferCommandRepository;
        this.transferReadRepository = transferReadRepository;
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
    public Optional<AccountDTO> transfert(TransferQueryDTO transferQuery){

        Optional<Account> fromAccount = accountRepository.findById(transferQuery.getFromtIdAccount());
        if(fromAccount.isEmpty()) throw new BankingOpperationException("issuing bank account was not found");

        Optional<Account> toAccount = accountRepository.findById(transferQuery.getToIdAccount());
        if(toAccount.isEmpty()) throw new BankingOpperationException("bank account was not found");

        TransferCommand transfer = makeTransfer(
                fromAccount.get(),
                toAccount.get(),
                transferQuery.getAmount(),
                transferQuery.getCurrency()
        );
        updateListTransactionByAccountId(transfer.getFromAccount().getId());
        updateListTransactionByAccountId(transfer.getToAccount().getId());

        return Optional.of( new AccountDTO(transfer.getFromAccount()) );
    }

    public List<TransferOperationDTO> transferHistory(String accountId){
        try {
            List<TransferOperationDTO> transfers = transferReadRepository.findAllById( accountId );
            return transfers;
        }
        catch (BankingOpperationException e) {
            log.error(e.getMessage());
            return Collections.EMPTY_LIST;
        }
    }

    private TransferCommand makeTransfer(Account fromAccount, Account toAccount, Double amount, Currency currency){
        BankingOperation operation = new BankingOperation(fromAccount);
        TransferCommand transfer = operation.transfert(toAccount, amount, currency);
        transferCommandRepository.save(transfer);
        return transfer;
    }

    private void updateListTransactionByAccountId(String accountId){

        List<TransferCommand> transfers = transferCommandRepository.findAllByAccount_Id(
                accountId
        );
        List<TransferOperationDTO> transfersDto =transfers
                .stream()
                .map(t->{
                        return TransferOperationDTO
                                .builder()
                                .amount(t.getBalance())
                                .currency(t.getCurrency())
                                .date(t.getDate())
                                .toIdAccount(t.getToAccount().getId())
                                .fromtIdAccount(t.getFromAccount().getId())
                                .build();
                })
                .collect(Collectors.toList());
        transferReadRepository.save( accountId, transfersDto );

    }

}
