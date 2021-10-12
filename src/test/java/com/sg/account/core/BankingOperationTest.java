package com.sg.account.core;

import com.sg.account.model.Account;
import com.sg.account.model.Currency;
import com.sg.account.model.TransferCommand;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BankingOperationTest {

    @Test
    void transfert() {

        // Initialisation des entities
        Account fromAccount = Account.builder().id("id1").accountBalance(1500.0).currency(Currency.EUR).build();
        Account toAccount = Account.builder().id("id2").accountBalance(1000.0).currency(Currency.EUR).build();

        BankingOperation operation = new BankingOperation(fromAccount);
        TransferCommand transfer = operation.transfert(toAccount, 112.5, Currency.EUR);

        assertThat(transfer.getFromAccount().getAccountBalance()).isEqualTo(1387.5);
        assertThat(transfer.getToAccount().getAccountBalance()).isEqualTo(1112.5);

    }

}
