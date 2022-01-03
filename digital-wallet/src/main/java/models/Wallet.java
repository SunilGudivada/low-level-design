package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Wallet {
    private User user;
    private double balance;
    private List<Transaction> transactions;

    public Wallet(User user, double balance) {
        this.user = user;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public void creditAmount(double amount) {
        this.balance += amount;
    }

    public void debitAmount(double amount) {
        this.balance -= amount;
    }
}
