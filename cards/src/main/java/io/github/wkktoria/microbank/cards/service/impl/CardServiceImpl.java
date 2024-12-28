package io.github.wkktoria.microbank.cards.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import io.github.wkktoria.microbank.cards.constants.CardsConstants;
import io.github.wkktoria.microbank.cards.dto.CardDto;
import io.github.wkktoria.microbank.cards.entity.Card;
import io.github.wkktoria.microbank.cards.exception.CardAlreadyExistsException;
import io.github.wkktoria.microbank.cards.exception.ResourceNotFoundException;
import io.github.wkktoria.microbank.cards.mapper.CardMapper;
import io.github.wkktoria.microbank.cards.repository.CardRepository;
import io.github.wkktoria.microbank.cards.service.ICardService;

@Service
public class CardServiceImpl implements ICardService {
	private final CardRepository cardRepository;

	public CardServiceImpl(final CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	@Override
	public void createCard(final String mobileNumber) {
		Optional<Card> optionalCard = cardRepository.findByMobileNumber(mobileNumber);

		if (optionalCard.isPresent()) {
			throw new CardAlreadyExistsException("Card has been already registered with the given mobile number.");
		}

		cardRepository.save(createNewCard(mobileNumber));
	}

	@Override
	public CardDto fetchCard(final String mobileNumber) {
		Card card = cardRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
		return CardMapper.mapToCardDto(card, new CardDto());
	}

	@Override
	public boolean updateCard(CardDto cardDto) {
		Card card = cardRepository.findByCardNumber(cardDto.getCardNumber())
				.orElseThrow(() -> new ResourceNotFoundException("Card", "cardNumber", cardDto.getCardNumber()));
		CardMapper.mapToCard(cardDto, card);
		cardRepository.save(card);
		return true;
	}

	@Override
	public boolean deleteCard(final String mobileNumber) {
		Card card = cardRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
		cardRepository.deleteById(card.getCardId());
		return true;
	}

	private Card createNewCard(final String mobileNumber) {
		Card newCard = new Card();
		long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
		newCard.setCardNumber(Long.toString(randomCardNumber));
		newCard.setMobileNumber(mobileNumber);
		newCard.setCardType(CardsConstants.CREDIT_CARD);
		newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
		newCard.setAmountUsed(0.0);
		newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
		return newCard;
	}
}
