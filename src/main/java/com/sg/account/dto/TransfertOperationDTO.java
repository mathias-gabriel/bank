package com.sg.account.dto;

import com.sg.account.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransfertOperationDTO {
    private String fromtIdAccount;
    private String toIdAccount;
    private Double amount;
    private Currency currency;
}
