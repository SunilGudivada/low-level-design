package services;

import exceptions.UserAlreadyExistsException;
import models.User;

/**
 * WalletService is the helper class for the WalletCreation, Overview Commands.
 */
public class WalletService {

    private final Database db;

    public WalletService(Database db){
        this.db = db;
    }

    /**
     * Creates the user and creates associated wallet to the user.
     * @param name user name
     * @param amount intial amount to create the wallet
     * @throws UserAlreadyExistsException If user already present in the system it throws this exception
     */
    public void createWallet(String name, double amount) throws UserAlreadyExistsException {

        if(this.db.userDB.containsKey(name)){
            throw new UserAlreadyExistsException("user already exists in the system");
        }

        User user = new User(name);
        user.createWallet(amount);
        this.db.userDB.put(name, user);
    }

    /**
     * Print the current state of the users in the system.
     */
    public void overview(){
        System.out.println("Overview:::: ");
        for(User user : this.db.userDB.values()){
            System.out.println(user.getName()+" "+user.getWallet().getBalance());
        }
    }
}
