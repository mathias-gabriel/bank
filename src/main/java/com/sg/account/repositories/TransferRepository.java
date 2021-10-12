package com.sg.account.repositories;

import com.sg.account.model.TransferCommand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends CrudRepository<TransferCommand, String> {

}
