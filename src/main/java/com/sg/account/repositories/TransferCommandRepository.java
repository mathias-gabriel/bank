package com.sg.account.repositories;

import com.sg.account.model.TransferCommand;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferCommandRepository extends CrudRepository<TransferCommand, String> {

    @Query("from TransferCommand as t where t.fromAccount.id = ?1 or t.toAccount.id = ?1")
    List<TransferCommand> findAllByAccount_Id(String accountId);
}
