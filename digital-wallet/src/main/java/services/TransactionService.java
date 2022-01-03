package services;

import enums.TransactionType;
import exceptions.InsufficientBalanceException;
import exceptions.UserNotFoundException;
import models.Transaction;
import models.User;

import java.util.UUID;

/**
 * Service class for the TransferMoney, Statement commands
 */
public class TransactionService {

    private final Database db;

    public TransactionService(Database db){
        this.db = db;
    }

    public void TransferMoney(String from, String to, double amount) throws InsufficientBalanceException, UserNotFoundException {

        //Todo validation on amount to be added here
        // Validation to check if the from and to users exist in the system

        User fromUser = db.userDB.get(from);
        User toUser = db.userDB.get(to);
        if(fromUser == null || toUser == null ){
            throw new UserNotFoundException("User does not exist in the system");
        }

        if (fromUser.getWallet().getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }

        fromUser.getWallet().debitAmount(amount);
        toUser.getWallet().creditAmount(amount);

        this.addTransaction(fromUser, toUser, TransactionType.DEBIT, amount);
        this.addTransaction(toUser, fromUser, TransactionType.CREDIT, amount);

    }

    private void addTransaction(User fromuser, User toUser, TransactionType type, double amount) {
        fromuser.getWallet().getTransactions().add(new Transaction(UUID.randomUUID(), type, toUser, amount));
    }

    /**
     * Prints the account statement of the user
     * @param name username
     */
    public void Statement(String name){
        System.out.println("\n Statement for "+name);
        for(Transaction transaction : db.userDB.get(name).getWallet().getTransactions()){
            String transactionType  = TransactionType.CREDIT == transaction.getTransactionType() ? "credit" : "debit";
            System.out.println(transaction.getUser().getName()+" "+transactionType +" " +transaction.getAmount());
        }
    }
}
