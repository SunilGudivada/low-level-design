package models;

import lombok.Getter;

import java.util.UUID;

@Getter
public class User {
    private final UUID userIdentifier;
    private final String name;
    private Wallet wallet;

    public User(String name) {
        this.userIdentifier = UUID.randomUUID();
        this.name = name;
    }

    public void createWallet(double amount){
       this.wallet = new Wallet(this, amount);
    }

    public String toString(){

        return this.name + " "+this.getWallet().getBalance();
    }

}
