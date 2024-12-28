package io.github.wkktoria.microbank.accounts.controller;

import io.github.wkktoria.microbank.accounts.constants.AccountsConstants;
import io.github.wkktoria.microbank.accounts.dto.CustomerDto;
import io.github.wkktoria.microbank.accounts.dto.ErrorResponseDto;
import io.github.wkktoria.microbank.accounts.dto.ResponseDto;
import io.github.wkktoria.microbank.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Accounts in MicroBank",
        description = "CRUD REST API in MicroBank to create, update, fetch, and delete account details."
)
@RestController
@RequestMapping(path = "/api/v1/account", produces = {
        MediaType.APPLICATION_JSON_VALUE
})
@AllArgsConstructor
@Validated
class AccountController {
    private IAccountService accountService;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new customer and account in MicroBank."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP 201 Created"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP 500 Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PostMapping("/create")
    ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch customer and account details based on mobile number."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP 200 OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP 500 Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",
            message = "Mobile number must be 10 digits.") final String mobileNumber) {
        final CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update customer and account details based on mobile number."
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "HTTP 200 OK"),
                    @ApiResponse(responseCode = "417", description = "HTTP 417 Expectation Failed"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP 500 Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @PutMapping("/update")
    ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);

        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }

        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
    }

    @Operation(
            summary = "Delete Account and Customer Details REST API",
            description = "REST API to delete customer and account details based on mobile number."
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "HTTP 200 OK"),
                    @ApiResponse(responseCode = "417", description = "HTTP 417 Expectation Failed"),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP 500 Internal Server Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @DeleteMapping("/delete")
    ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",
            message = "Mobile number must be 10 digits.") final String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);

        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }

        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
    }
}
