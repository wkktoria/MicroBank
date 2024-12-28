package io.github.wkktoria.microbank.cards.service;

import io.github.wkktoria.microbank.cards.dto.CardDto;

public interface ICardService {
	void createCard(final String mobileNumber);

	CardDto fetchCard(final String mobileNumber);

	boolean updateCard(CardDto cardDto);

	boolean deleteCard(final String mobileNumber);
}
