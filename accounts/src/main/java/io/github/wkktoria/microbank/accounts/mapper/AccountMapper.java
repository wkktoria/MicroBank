package io.github.wkktoria.microbank.accounts.mapper;

import io.github.wkktoria.microbank.accounts.dto.AccountDto;
import io.github.wkktoria.microbank.accounts.entity.Account;

public class AccountMapper {
    public static AccountDto mapToAccountDto(Account account, AccountDto accountDto) {
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBranchAddress(account.getBranchAddress());
        return accountDto;
    }

    public static Account mapToAccount(AccountDto accountDto, Account account, boolean update) {
        if (!update) {
            account.setAccountNumber(accountDto.getAccountNumber());
        }
        account.setAccountType(accountDto.getAccountType());
        account.setBranchAddress(accountDto.getBranchAddress());
        return account;
    }
}
