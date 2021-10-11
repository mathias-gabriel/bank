package com.sg.account.core;

public class BankingOpperationException  extends RuntimeException{

    public BankingOpperationException(String message) {
        super(message);
    }

    public BankingOpperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankingOpperationException(Throwable cause) {
        super(cause);
    }
}
