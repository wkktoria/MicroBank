package io.github.wkktoria.microbank.accounts.service.impl;

import io.github.wkktoria.microbank.accounts.constants.AccountConstants;
import io.github.wkktoria.microbank.accounts.dto.AccountDto;
import io.github.wkktoria.microbank.accounts.dto.CustomerDto;
import io.github.wkktoria.microbank.accounts.entity.Account;
import io.github.wkktoria.microbank.accounts.entity.Customer;
import io.github.wkktoria.microbank.accounts.exception.CustomerAlreadyExistsException;
import io.github.wkktoria.microbank.accounts.exception.ResourceNotFoundException;
import io.github.wkktoria.microbank.accounts.mapper.AccountMapper;
import io.github.wkktoria.microbank.accounts.mapper.CustomerMapper;
import io.github.wkktoria.microbank.accounts.repository.AccountRepository;
import io.github.wkktoria.microbank.accounts.repository.CustomerRepository;
import io.github.wkktoria.microbank.accounts.service.IAccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements IAccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountServiceImpl(final AccountRepository accountRepository,
                              final CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with mobile number "
                    + customerDto.getMobileNumber() + " has been already registered.");
        }

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");

        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccount(final String mobileNumber) {
        final Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        final Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));

        return customerDto;
    }

    private Account createNewAccount(final Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        int randomAccountNumber = 1000000000 + new Random().nextInt(90000000);

        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");

        return newAccount;
    }
}
