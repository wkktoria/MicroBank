package io.github.wkktoria.microbank.accounts.controller;

import io.github.wkktoria.microbank.accounts.constants.AccountConstants;
import io.github.wkktoria.microbank.accounts.dto.CustomerDto;
import io.github.wkktoria.microbank.accounts.dto.ResponseDto;
import io.github.wkktoria.microbank.accounts.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/account", produces = {
        MediaType.APPLICATION_JSON_VALUE
})
@AllArgsConstructor
@Validated
class AccountController {
    private IAccountService accountService;

    @PostMapping("/create")
    ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",
            message = "Mobile number must be 10 digits.") final String mobileNumber) {
        final CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @PutMapping("/update")
    ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);

        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
    }

    @DeleteMapping("/delete")
    ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",
            message = "Mobile number must be 10 digits.") final String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);

        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
    }
}
