package io.github.wkktoria.microbank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold customer and account information."
)
public class CustomerDto {
    @Schema(
            description = "Name of the customer.",
            example = "Jan Kowalski"
    )
    @NotEmpty(message = "Name cannot be null or empty.")
    @Size(min = 5, max = 30,
            message = "The length of the customer name should be between 5 and 30.")
    private String name;

    @Schema(
            description = "Email address of the customer.",
            example = "jankowalski@gmail.com"
    )
    @NotEmpty(message = "Email address cannot be null or empty.")
    @Email(message = "Email address should be valid.")
    private String email;

    @Schema(
            description = "Mobile number of the customer.",
            example = "1234567890"
    )
    @NotEmpty(message = "Mobile number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer."
    )
    private AccountDto accountDto;
}
