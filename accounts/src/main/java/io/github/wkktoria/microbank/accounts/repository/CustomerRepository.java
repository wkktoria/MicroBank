package io.github.wkktoria.microbank.accounts.repository;

import io.github.wkktoria.microbank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByMobileNumber(final String mobileNumber);
}
