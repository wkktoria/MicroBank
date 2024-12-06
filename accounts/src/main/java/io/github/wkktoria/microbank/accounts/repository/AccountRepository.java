package io.github.wkktoria.microbank.accounts.repository;

import io.github.wkktoria.microbank.accounts.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByCustomerId(final Integer customerId);

    @Transactional
    @Modifying
    void deleteByCustomerId(final Integer customerId);
}
