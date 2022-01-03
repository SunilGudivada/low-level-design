package models;

import enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    UUID transactionIdentifier;
    TransactionType transactionType;
    User user;
    double amount;

    //TODO add timestamp attribute here


}
