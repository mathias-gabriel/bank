package com.sg.account.dto;

import com.sg.account.model.Currency;
import com.sg.account.model.TransferCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferQueryDTO implements Serializable {
    private String fromtIdAccount;
    private String toIdAccount;
    private Double amount;
    private Currency currency;

}
