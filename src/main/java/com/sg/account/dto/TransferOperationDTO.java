package com.sg.account.dto;

import com.sg.account.model.Currency;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Builder
public class TransferOperationDTO implements Serializable{

    private static final long serialVersionUID = -7503626305588041809L;
    private String fromtIdAccount;
    private String toIdAccount;
    private Double amount;
    private Currency currency;
    private OffsetDateTime date;

}
