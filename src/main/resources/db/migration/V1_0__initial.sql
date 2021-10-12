CREATE TABLE account (
    id                  varchar(80) PRIMARY KEY,
    account_balance     float,
    currency            varchar(32)
);

CREATE TABLE transfer (
                          id                  varchar(80) PRIMARY KEY,
                          from_account_id     varchar(80),
                          to_account_id       varchar(80),
                          balance             float,
                          date                timestamp with time zone,
                          currency            varchar(32),
                          CONSTRAINT fk_from_account FOREIGN KEY(from_account_id) REFERENCES account(id),
                          CONSTRAINT fk_to_account FOREIGN KEY(to_account_id) REFERENCES account(id)
);
