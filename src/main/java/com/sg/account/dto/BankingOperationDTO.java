package com.sg.account.dto;

import com.sg.account.model.Currency;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankingOperationDTO {
    private String idAccount;
    private Double amount;
    private Currency currency;
}
