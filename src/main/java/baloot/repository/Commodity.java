package baloot.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import java.util.*;

//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Commodity {
    private int id;
    private String name;
    private int providerId;
    private int price;
    private List<String> categories = new ArrayList<>();
    private double rating;
    private int inStock;
    @JsonIgnore
    private List<Double> totalRating = new ArrayList<>();
    Map<Integer,Comment> comments = new HashMap<>();
    public Map<Integer, Comment> getComments() {
        return comments;
    }
    public Commodity(){}
    public void addComment(Comment comment){
        comments.put(comment.getId(), comment);
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public Commodity(int id, String name, int providerId, int price, List<String> categories,
                     double rating, int inStockCount){
        this.id = id;
        this.name = name;
        this.providerId = providerId;
        this.price = price;
        this.categories = categories;
        this.rating = rating;
        this.inStock = inStockCount;
        totalRating.add(rating);
    }

    public void updateInStock(){
        inStock--;
    }

    public int getInStock(){
        return inStock;
    }

    public int getId(){
        return id;
    }

    public List<String> getCategories(){
        return categories;
    }

    public double getRating(){
        return (double) (Math.floor(rating * 100) / 100);
    }

    public void setRating(double rating){
        this.rating = rating;
    }
    public void updateRating(double rate,boolean newRating){
        totalRating.add(rate);
        double sum = rating;
        for(double num:totalRating) {
            sum += num;
        }
        rating = sum/(totalRating.size()+1);
    }

}
