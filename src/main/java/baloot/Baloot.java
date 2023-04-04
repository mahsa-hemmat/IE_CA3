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

    public DataBase getDataBase(){
        return db;
    }

    public User getLoggedInUser() {
        return db.getLoggedInUser();
    }

    public Boolean hasAnyUserLoggedIn(){
        return db.hasAnyUserLoggedIn();
    }

    public Boolean isUserValid(String username) throws Exception{
        return db.getUserById(username) != null;
    }

    public void loginInUser(String username, String password) throws Exception{
        db.setLoggedInUser(username, password);
    }

    public void logOutUser(){db.logOutUser();}

    public void addData(List<User> users, List<Provider> providers, List<Commodity> commodities, List<Comment> comments, List<Discount> discounts) throws Exception {
        db.addUser(users);
        db.addProvider(providers);
        db.addCommodity(commodities);
        db.addComment(comments);
        db.addDiscount(discounts);
    }
    public void increaseCredit(int credit){db.increaseCredit(credit);}

    public Provider getProvider(int id) throws Exception { return db.getProviderById(id); }
}
