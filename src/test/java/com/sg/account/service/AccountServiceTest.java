package com.sg.account.service;

import com.sg.account.dto.AccountDTO;
import com.sg.account.dto.BankingOperationDTO;
import com.sg.account.dto.TransfertOperationDTO;
import com.sg.account.model.Account;
import com.sg.account.model.Currency;
import com.sg.account.repositories.AccountRepository;
import com.sg.account.repositories.TransferRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransferRepository transferRepository;

    @Test
    void withdrawAmountOfMoney() {

        // Initialisation des entities
        Account account = Account.builder().id("id1").accountBalance(150.0).build();
        // Définition des fonctions mockées
        when(accountRepository.findById("id1") ).thenReturn( Optional.of(account) );

        // Given
        BankingOperationDTO bankingOperationDTO = BankingOperationDTO
                .builder()
                .amount(10.0)
                .idAccount("id1")
                .build();

        // When
        Optional<AccountDTO> accountDTO = accountService.withdrawAmountOfMoney(bankingOperationDTO);

        // Then
        assertThat(accountDTO.isPresent()).isTrue();
        assertThat(accountDTO.get().getAccountBalance()).isEqualTo(140.0);

    }

    @Test
    void depositAmountOfMoney() {

        // Initialisation des entities
        Account account = Account.builder().id("id1").accountBalance(150.0).build();
        // Définition des fonctions mockées
        when(accountRepository.findById("id1") ).thenReturn( Optional.of(account) );

        // Given
        BankingOperationDTO bankingOperationDTO = BankingOperationDTO
                .builder()
                .amount(10.0)
                .idAccount("id1")
                .build();

        // When
        Optional<AccountDTO> accountDTO = accountService.depositAmountOfMoney(bankingOperationDTO);

        // Then
        assertThat(accountDTO.isPresent()).isTrue();
        assertThat(accountDTO.get().getAccountBalance()).isEqualTo(160.0);

    }

    @Test
    void transfert() {
        // Initialisation des entities
        Account fromAccount = Account.builder().id("id1").accountBalance(1500.0).currency(Currency.EUR).build();
        Account toAccount = Account.builder().id("id2").accountBalance(1000.0).currency(Currency.EUR).build();

        // Définition des fonctions mockées
        when(accountRepository.findById("id1") ).thenReturn( Optional.of(fromAccount) );
        when(accountRepository.findById("id2") ).thenReturn( Optional.of(toAccount) );

        // Given
        TransfertOperationDTO transfertOperationDTO = TransfertOperationDTO
                .builder()
                .amount(102.5)
                .fromtIdAccount("id1")
                .toIdAccount("id2")
                .currency(Currency.EUR)
                .build();

        // When
        Optional<AccountDTO> accountDTO = accountService.transfert(transfertOperationDTO);

        assertThat(accountDTO.isPresent()).isTrue();
        assertThat(accountDTO.get().getAccountBalance()).isEqualTo(1397.5);
    }
}
