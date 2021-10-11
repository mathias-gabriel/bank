package com.sg.account.repositories;

import com.sg.account.model.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    @Transactional
    //@Modifying(clearAutomatically = true, flushAutomatically = true)
    @Modifying(clearAutomatically = true)
    @Query("update Account a set a.accountBalance = :accountBalance where a.id = :id")
    void updateAccountBalanceById(@Param("id") String id, @Param("accountBalance") Double accountBalance);
}
