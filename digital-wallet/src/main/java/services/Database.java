package services;

import models.User;

import java.util.*;

public class Database {

    HashMap<String, User> userDB;

    public Database(){
        this.userDB = new HashMap<>();
    }
}
