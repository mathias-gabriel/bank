package com.sg.account.controller;

import com.sg.account.core.BankingOpperationException;
import com.sg.account.dto.AccountDTO;
import com.sg.account.dto.BankingOperationDTO;
import com.sg.account.dto.TransferOperationDTO;
import com.sg.account.dto.TransferQueryDTO;
import com.sg.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountID}")
    public AccountDTO getAccount(
            @RequestParam(name = "accountID", required = false) final String accountId) {

                return null;
    }

    @PutMapping("/withdraw")
    public ResponseEntity<AccountDTO> withdraw(
            @RequestBody BankingOperationDTO bankingOperationDTO){

        try {
            Optional<AccountDTO> account = accountService.withdrawAmountOfMoney(bankingOperationDTO);
            return ResponseEntity.ok(account.get());
        }
        catch (BankingOpperationException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/deposite")
    public ResponseEntity<AccountDTO> deposite(
            @RequestBody BankingOperationDTO bankingOperationDTO){

        try {
            Optional<AccountDTO> account = accountService.depositAmountOfMoney(bankingOperationDTO);
            return ResponseEntity.ok(account.get());
        }
        catch (BankingOpperationException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/transfert")
    public ResponseEntity<AccountDTO> transfert(
            @RequestBody TransferQueryDTO transfer){

        try {
            Optional<AccountDTO> account = accountService.transfert(transfer);
            return ResponseEntity.ok(account.get());
        }
        catch (BankingOpperationException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/history/{accountID}")
    public ResponseEntity<List<TransferOperationDTO>> history(
            @RequestParam(name = "accountID", required = false) final String accountId){

            List<TransferOperationDTO> transfers = accountService.transferHistory(accountId);
            return ResponseEntity.ok(transfers);
    }

}
