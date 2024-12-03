package io.github.wkktoria.microbank.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {
    private Integer customerId;

    @Id
    private Integer accountNumber;

    private String accountType;

    private String branchAddress;
}
