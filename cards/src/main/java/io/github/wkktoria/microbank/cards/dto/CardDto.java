package io.github.wkktoria.microbank.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(name = "Card", description = "Schema to hold card information.")
public class CardDto {
	@Schema(description = "Mobile number of the customer.", example = "8087339090")
	@NotEmpty(message = "Mobile number cannot be null or empty.")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
	private String mobileNumber;

	@Schema(description = "Card number of the customer.", example = "109283937821")
	@NotEmpty(message = "Card number cannot be null or empty.")
	@Pattern(regexp = "(^$|[0-9]{12})", message = "Card number must be 12 digits.")
	private String cardNumber;

	@Schema(description = "Type of the card.", example = "Credit Card")
	@NotEmpty(message = "Card type cannot be null or empty.")
	private String cardType;

	@Schema(description = "Total amount limit available.", example = "1000")
	@Positive(message = "Total card limit should be greater than zero.")
	private Double totalLimit;

	@Schema(description = "Total amount used by a customer.", example = "100")
	@PositiveOrZero(message = "Total amount used should be equal or greater than zero.")
	private Double amountUsed;

	@Schema(description = "Total available amount.", example = "900")
	@PositiveOrZero(message = "Total available amount should be equal or greater than zero.")
	private Double availableAmount;
}
