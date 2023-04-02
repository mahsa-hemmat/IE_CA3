package baloot;

import baloot.repository.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Baloot {

    private DataBase db = new DataBase();
    private static Baloot instance;


    private Baloot() {}

    public static Baloot getInstance() {
        if(instance == null) {
            instance = new Baloot();
        }
        return instance;
    }

    public String getLoggedInUser() {
        return db.getLoggedInUser();
    }

    public Boolean hasAnyUserLoggedIn(){
        return db.hasAnyUserLoggedIn();
    }

    public Boolean isUserValid(String username){
        return db.getUserById(username) != null;
    }

    public void loginInUser(String username){
        db.setLoggedInUser(username);
    }
    public void logOutUser(){db.logOutUser();}

    public void addData(List<User> users, List<Provider> providers, List<Commodity> commodities, List<Comment> comments) throws Exception {
        db.addUser(users);
        db.addProvider(providers);
        db.addCommodity(commodities);
        db.addComment(comments);
    }
    public void increaseCredit(int credit){db.increaseCredit(credit);}
}
