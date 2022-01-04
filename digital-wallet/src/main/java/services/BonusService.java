package services;

import enums.TransactionType;
import models.Transaction;
import models.User;

import java.util.List;

public class BonusService {

    private final Database db;

    BonusService(Database db){
        this.db = db;
    }

    public void FixedDeposit(double amount){
        for(User user: this.db.userDB.values()){
            List<Transaction> transactions = user.getWallet().getTransactions();

            int prev =0;
            int numberOfTransactions = transactions.size();
            int counter = numberOfTransactions -1;
            if(transactions.size() < 5) return;
            while(counter >0){
                TransactionType type = transactions.get(counter).getTransactionType();

                if(type == TransactionType.CREDIT) {
                    // Remove the amount from the current balance and check
                }else {
                    // Add the amount from the current balance and check
                }
                counter --;
            }
        }
    }
}
