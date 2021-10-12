package com.sg.account.repositories;

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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureEmbeddedDatabase
public class TransferCommandRepositoryTest {

    @Autowired
    private TransferCommandRepository transferCommandRepository;

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

        transferCommandRepository.save(transfer);

        Iterable<TransferCommand> transfers = transferCommandRepository.findAll();
        int nOfAccount = 1;
        assertThat(transfers).hasSize(nOfAccount);
    }

}
