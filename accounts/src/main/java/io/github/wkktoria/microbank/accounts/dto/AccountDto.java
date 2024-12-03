package io.github.wkktoria.microbank.accounts.dto;

import lombok.Data;

@Data
public class AccountDto {
    private Integer accountNumber;

    private String accountType;

    private String branchAddress;
}
