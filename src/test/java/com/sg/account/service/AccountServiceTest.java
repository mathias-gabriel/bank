package com.sg.account.service;

import com.sg.account.dto.AccountDTO;
import com.sg.account.dto.BankingOperationDTO;
import com.sg.account.model.Account;
import com.sg.account.repositories.AccountRepository;
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
}
