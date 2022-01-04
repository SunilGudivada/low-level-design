package services;

import enums.TransactionType;
import models.Transaction;
import models.User;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class OfferService {

    private final Database db;

    private boolean isActive = false;
    private double amount = 0;
    private final User offer1;
    private final User offer2;

    public OfferService(Database database) {
        this.db = database;
        this.offer1 = new User("offer1");
        this.offer2 = new User("offer2");
    }

    public void activateOffer1(double amount) {
        this.isActive = true;
        this.amount = amount;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public boolean checkIsActive() {
        return this.isActive;
    }


    public void applyOffer1(String from, String to) {

        if (this.isActive) {

            User fromUser = db.userDB.get(from);
            User toUser = db.userDB.get(to);

            double fromUserAccountBalance = fromUser.getWallet().getBalance();
            double toUserAccountBalance = toUser.getWallet().getBalance();

            if (fromUserAccountBalance == toUserAccountBalance) {
                fromUser.getWallet().creditAmount(this.amount);
                toUser.getWallet().creditAmount(this.amount);
                fromUser.getWallet().getTransactions().add(new Transaction(UUID.randomUUID(), TransactionType.CREDIT, this.offer1, amount));
                toUser.getWallet().getTransactions().add(new Transaction(UUID.randomUUID(), TransactionType.CREDIT, this.offer1, amount));
            }

        }
    }

    public void applyOffer2() {
        PriorityQueue<User> pq = new PriorityQueue<>((user1, user2) -> {

            List<Transaction> user1Transactions = user1.getWallet().getTransactions();
            List<Transaction> user2Transactions = user2.getWallet().getTransactions();

            if (user1Transactions.size() != user2Transactions.size()) {
                return user2Transactions.size() - user1Transactions.size();
            }

            double user1AccountBalance = user1.getWallet().getBalance();
            double user2AccountBalance = user2.getWallet().getBalance();

            if (user1AccountBalance != user2AccountBalance) {
                return user2AccountBalance > user1AccountBalance ? -1 : 1;
            }

            LocalDateTime user1CreatedOn = user1.getWallet().getCreatedOn();
            LocalDateTime user2CreatedOn = user1.getWallet().getCreatedOn();

            if (user1CreatedOn != user2CreatedOn) {
                return user1CreatedOn.isBefore(user2CreatedOn) ? -1 : 1;
            }

            return 0;

        });

        pq.addAll(this.db.userDB.values());


        int[] offer = new int[]{10, 5, 2};

        for (int amount : offer) {
            System.out.println(pq.toString());
            User user = pq.poll();
            if (user != null) {
                user.getWallet().creditAmount(amount);
                user.getWallet().getTransactions().add(new Transaction(UUID.randomUUID(), TransactionType.CREDIT, this.offer2, amount));
            }
        }
    }
}
