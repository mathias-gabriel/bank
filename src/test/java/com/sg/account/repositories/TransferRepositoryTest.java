package com.sg.account.repositories;

import com.sg.account.core.BankingOperation;
import com.sg.account.model.Account;
import com.sg.account.model.Currency;
import com.sg.account.model.TransferCommand;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureEmbeddedDatabase
public class TransferRepositoryTest {

    @Autowired
    private TransferRepository transferRepository;

    @Test
    public void should_find_all_accounts() {

        Account fromAccount = Account
                .builder()
                .accountBalance(225.5)
                .id("id1")
                .currency(Currency.EUR)
                .build();

        Account toAccount = Account
                .builder()
                .accountBalance(15.5)
                .id("id2")
                .currency(Currency.EUR)
                .build();

        TransferCommand transfer = TransferCommand.builder()
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .currency(Currency.EUR)
                .date(OffsetDateTime.now())
                .balance(10.3)
                .build();

        transferRepository.save(transfer);

        Iterable<TransferCommand> transfers = transferRepository.findAll();
        int nOfAccount = 1;
        assertThat(transfers).hasSize(nOfAccount);
    }

}
