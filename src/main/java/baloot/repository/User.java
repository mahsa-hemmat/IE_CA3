package baloot.repository;

import baloot.exception.*;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String password;
    private String email;
    private String birthDate;
    private String address;
    private int credit;
    private BuyList buyList = new BuyList();
    private Map<Integer, Commodity> purchasedList = new HashMap<>();
    private Map<Integer, Integer> ratings;

    public User(){
        ratings=new HashMap<>();
    }
    public User(String username, String password, String email, String birthDate, String address, int credit){
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.credit = credit;
    }
    //addUser {"username":"ali","password":"ali","email":"ali","birthDate":"ali","address":"ali","credit":"5.5"}
    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public BuyList getBuyList(){ return buyList; }

    public int getRating(int rating, int id){
        int curr_rating=rating;
        if(ratings.containsKey(id)){
            curr_rating=rating-ratings.get(id);
        }
        ratings.put(id,rating);
        return curr_rating;
    }
    public boolean hasRating(int id){
        return ratings.containsKey(id);
    }

    public void increaseCredit(int amount) {
        credit+=amount;
    }
    public Map<Integer, Commodity> getPurchasedList(){ return purchasedList; }
    public void completePurchase() throws Exception{
        if(credit < buyList.getTotalCost()) {
            throw new NotEnoughCreditException("");
        }
        credit-=buyList.getTotalCost();
        purchasedList.putAll(buyList.getCommodities());
        for(Commodity commodity:buyList.getCommodities().values())
            commodity.updateInStock();
        buyList=new BuyList();
    }
}