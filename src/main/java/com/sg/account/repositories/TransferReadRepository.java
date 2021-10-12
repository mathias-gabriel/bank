package com.sg.account.repositories;

import com.sg.account.core.BankingOpperationException;
import com.sg.account.dto.TransferOperationDTO;
import com.sg.account.model.TransferCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransferReadRepository {

    @Autowired
    private RedisTemplate<String, List<TransferOperationDTO>> redisTemplate;

    public void save(String idAccount, List<TransferOperationDTO> transfers) {
        redisTemplate.opsForValue().set(idAccount, transfers);
    }

    public List<TransferOperationDTO> findAllById(String id) {
        if(!redisTemplate.hasKey(id))
            throw new BankingOpperationException("the account ("+id+") has no transfer history");

        return redisTemplate.opsForValue().get(id);
    }

}
