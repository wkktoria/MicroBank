package io.github.wkktoria.microbank.accounts.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private String name;

    private String email;

    private String mobileNumber;

    private AccountDto accountDto;
}
