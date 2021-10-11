package com.sg.account.repositories;

import com.sg.account.core.BankingOperation;
import com.sg.account.model.Account;
import com.sg.account.model.Currency;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureEmbeddedDatabase
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void should_find_all_accounts() {

        Account account = Account
                .builder()
                .accountBalance(5.5)
                .id("id1")
                .currency(Currency.EUR)
                .build();
        accountRepository.save(account);

        Iterable<Account> accounts = accountRepository.findAll();
        int nOfAccount = 1;
        assertThat(accounts).hasSize(nOfAccount);
    }

    @Test
    public void should_be_able_to_update_an_account() {

        String id = "id1";
        Account account = Account
                .builder()
                .accountBalance(150.0)
                .id(id)
                .currency(Currency.EUR)
                .build();
        accountRepository.save(account);

        BankingOperation operation = new BankingOperation(account);

        operation.depositAmountOfMoney(10.0);
        accountRepository.save(operation.getAccount());
        Optional<Account> accountFound = accountRepository.findById(id);
        assertThat(accountFound.isPresent()).isTrue();
        assertThat(accountFound.get().getAccountBalance() ).isEqualTo(160.0);

        operation.withdrawAmountOfMoney(30.0);
        accountRepository.save(operation.getAccount());
        accountFound = accountRepository.findById(id);
        assertThat(accountFound.isPresent()).isTrue();
        assertThat(accountFound.get().getAccountBalance() ).isEqualTo(130.0);

    }
}
