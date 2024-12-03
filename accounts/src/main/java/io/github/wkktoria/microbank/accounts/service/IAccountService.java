package io.github.wkktoria.microbank.accounts.service;

import io.github.wkktoria.microbank.accounts.dto.CustomerDto;

public interface IAccountService {
    void createAccount(CustomerDto customerDto);
}
