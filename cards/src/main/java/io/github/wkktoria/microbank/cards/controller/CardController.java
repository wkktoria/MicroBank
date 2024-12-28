package io.github.wkktoria.microbank.cards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.wkktoria.microbank.cards.constants.CardsConstants;
import io.github.wkktoria.microbank.cards.dto.CardDto;
import io.github.wkktoria.microbank.cards.dto.ErrorResponseDto;
import io.github.wkktoria.microbank.cards.dto.ResponseDto;
import io.github.wkktoria.microbank.cards.service.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@Tag(name = "CRUD REST APIs for Cards in MicroBank", description = "CRUD REST API in MicroBank to create, update, fetch, and delete card details.")
@RestController
@RequestMapping(path = "/api/v1/card", produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
@Validated
public class CardController {
	private ICardService cardService;

	@Operation(summary = "Create card REST API", description = "REST API to create new card in MircoBank.")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP 201 Created"),
			@ApiResponse(responseCode = "500", description = "HTTP 500 Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PostMapping("/create")
	ResponseEntity<ResponseDto> createCard(
			@Valid @RequestParam @NotEmpty @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.") final String mobileNumber) {
		cardService.createCard(mobileNumber);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
	}

	@Operation(summary = "Fetch card details REST API", description = "REST API to fetch card details based on mobile number.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status Ok"),
			@ApiResponse(responseCode = "500", description = "HTTP 500 Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("/fetch")
	ResponseEntity<CardDto> fetchCardDetails(
			@RequestParam @NotEmpty @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.") final String mobileNumber) {
		CardDto cardDto = cardService.fetchCard(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(cardDto);
	}

	@Operation(summary = "Update card details REST API", description = "REST API to update card details based on card number.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status Ok"),
			@ApiResponse(responseCode = "417", description = "HTTP 417 Expectation Failed"),
			@ApiResponse(responseCode = "500", description = "HTTP 500 Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PutMapping("/update")
	ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardDto cardDto) {
		final boolean isUpdated = cardService.updateCard(cardDto);

		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
		}
	}

	@Operation(summary = "Delete card details REST API", description = "REST API to delete card details based on mobile number.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status Ok"),
			@ApiResponse(responseCode = "417", description = "HTTP 417 Expectation Failed"),
			@ApiResponse(responseCode = "500", description = "HTTP 500 Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@DeleteMapping("/delete")
	ResponseEntity<ResponseDto> deleteCardDetails(
			@RequestParam @NotEmpty @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.") final String mobileNumber) {
		final boolean isDeleted = cardService.deleteCard(mobileNumber);

		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
		}
	}
}
