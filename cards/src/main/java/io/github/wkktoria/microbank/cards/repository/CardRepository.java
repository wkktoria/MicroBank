package io.github.wkktoria.microbank.cards.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.wkktoria.microbank.cards.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
	Optional<Card> findByMobileNumber(final String mobileNumber);

	Optional<Card> findByCardNumber(final String cardNumber);
}
