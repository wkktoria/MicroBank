package io.github.wkktoria.microbank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold account information."
)
public class AccountDto {
    @Schema(
            description = "Account number of the MicroBank account.",
            example = "4830580961"
    )
    @NotEmpty(message = "Account number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits.")
    private Integer accountNumber;

    @Schema(
            description = "Account type of MicroBank account.",
            example = "Savings"
    )
    @NotEmpty(message = "Account type cannot be null or empty.")
    private String accountType;

    @Schema(
            description = "MicroBank branch address.",
            example = "123 Main Street, New York"
    )
    @NotEmpty(message = "Branch address cannot be null or empty.")
    private String branchAddress;
}
