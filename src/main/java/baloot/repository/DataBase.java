package baloot.repository;

import com.fasterxml.jackson.databind.JsonNode;
import baloot.exception.*;


import java.util.*;
import java.util.regex.Pattern;

public class DataBase {
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]+$";

    private Map<String, User> users;
    private Map<Integer, Provider> providers;
    private CommodityList commodities;
    private User loggedInUser;

    public DataBase(){
        users = new HashMap<>();
        providers = new HashMap<>();
        commodities = new CommodityList();
        loggedInUser = null;
    }
    private static final Pattern pattern = Pattern.compile(USERNAME_PATTERN);

    public CommodityList getCommodities() {
        return commodities;
    }

    public Provider getProviderById(int id) throws Exception {
        if(!(providers.containsKey(id)))
            throw new ProviderNotFoundException(id);
        return providers.get(id);
    }
    public void addUser(List<User> userData) throws Exception{
        for(User user: userData)
            if (!(pattern.matcher(user.getUsername()).find()))
                throw new InvalidUserException("InValidUsername: only alphanumeric characters are allowed in username");
            else
                users.put(user.getUsername(),user);
    }
    public void addProvider(List<Provider> providerData) throws Exception{
        for(Provider provider:providerData)
            providers.put(provider.getId(), provider);
    }
    public void addCommodity(List<Commodity> commodityData) throws Exception{
        for(Commodity commodity:commodityData)
            if(!(providers.containsKey(commodity.getProviderId())))
                throw new ProviderNotFoundException(commodity.getProviderId());
            else {
                commodities.addCommodity(commodity);
                providers.get(commodity.getProviderId()).addNewCommodity(commodity);
            }
    }

    public void addToBuyList(int commodityId, String username) throws Exception{
        if(commodities.getCommodityById(commodityId).getInStock() == 0)
            throw new OutOfStockException(commodityId);
        if(!(commodities.hasCommodity(commodityId)))
            throw new CommodityNotFoundException(commodityId);
        if(!(users.containsKey(username)))
            throw new UserNotFoundException("no user with given username is found");
        users.get(username).getBuyList().addCommodity(commodities.getCommodityById(commodityId));
    }

    public void removeFromBuyList(int commodityId, String username) throws Exception{
        if(!(users.containsKey(username)))
            throw new UserNotFoundException("no user with given username is found");
        users.get(username).getBuyList().removeCommodity(commodityId);
    }

    public void rateCommodity(int commodityId, String username, int score) throws Exception{
        if( (score<1) || (score > 10))
            throw new ScoreOutOfBoundsException("score range is from 1 to 10.");
        if(!(users.containsKey(username)))
            throw new UserNotFoundException("no user with given username is found");
        if(!(commodities.hasCommodity(commodityId)))
            throw new CommodityNotFoundException(commodityId);
        boolean newRating=!users.get(username).hasRating(commodityId);
        int update=users.get(username).getRating(score,commodityId);
        Commodity commodity = commodities.getCommodityById(commodityId);
        commodity.updateRating(update,newRating);
    }

    public void increaseCredit(int amount){
        users.get(loggedInUser.getUsername()).increaseCredit(amount);
    }

    public void rateCommodity(JsonNode data) throws Exception{
        System.out.println(data.get("score").asInt());
        if( (data.get("score").asInt()<1) || (data.get("score").asInt() > 10))
            throw new ScoreOutOfBoundsException("score range is from 1 to 10.");
        if(!(users.containsKey(data.get("username").asText())))
            throw new UserNotFoundException("no user with given username is found");
        if(!(commodities.hasCommodity(data.get("commodityId").asInt())))
            throw new CommodityNotFoundException(data.get("commodityId").asInt());
        boolean newRating=!users.get(data.get("username").asText()).hasRating(data.get("commodityId").asInt());
        int update=users.get(data.get("username").asText()).getRating(data.get("score").asInt(),data.get("commodityId").asInt());
        Commodity commodity = commodities.getCommodityById(data.get("commodityId").asInt());
        commodity.updateRating(update,newRating);
    }
    public void addToBuyList(JsonNode data) throws Exception{
        if(commodities.getCommodityById(data.get("commodityId").asInt()).getInStock() == 0)
            throw new OutOfStockException(data.get("commodityId").asInt());
        if(!(commodities.hasCommodity(data.get("commodityId").asInt())))
            throw new CommodityNotFoundException(data.get("commodityId").asInt());
        if(!(users.containsKey(data.get("username").asText())))
            throw new UserNotFoundException("no user with given username is found");
        users.get(data.get("username").asText()).getBuyList().addCommodity(commodities.getCommodityById(data.get("commodityId").asInt()));
    }
    public void removeFromBuyList(JsonNode data) throws Exception{
        if(!(users.containsKey(data.get("username").asText())))
            throw new UserNotFoundException("no user with given username is found");
        users.get(data.get("username").asText()).getBuyList().removeCommodity(data.get("commodityId").asInt());
    }
    public Commodity getCommodityById(int id) throws Exception{
        if(!(commodities.hasCommodity(id)))
            throw new CommodityNotFoundException(id);
        return commodities.getCommodityById(id);

    }
    public List<Commodity> getCommoditiesByPrice(int start, int end) throws Exception{
        return commodities.filterByPrice(start,end);
    }

    public List<Commodity> getCommoditiesByCategory(String category) throws Exception{
        return commodities.filterByCategory(new ArrayList<String>(Arrays.asList(category.split(" "))));
    }

    public void voteComment(String id, int vote) throws Exception {
        commodities.voteComment(id,vote);
    }

    public void addComment(List<Comment> comments) throws Exception {
        for(int i = 0; i<comments.size() ; i++){
            Comment comment = comments.get(i);
            int commodityId = comment.getcommodityId();
            if(!commodities.hasCommodity(commodityId))
                throw new CommodityNotFoundException(commodityId);
            comment.setId(i+1);
            commodities.getCommodityById(commodityId).addComment(comment);
        }
    }

    public String getUserByEmail(String userEmail) throws UserNotFoundException {
        for (User user: users.values())
            if(user.getEmail().equals(userEmail))
                return user.getUsername();
        throw new UserNotFoundException(userEmail);

    }

    public User getUserById(String id)  {
        if(!users.containsKey(id))
            return null;
        return users.get(id);
    }

    public void setLoggedInUser(String username) {
        this.loggedInUser = users.get(username);
    }

    public void logOutUser(){
        loggedInUser = null;
    }

    public User getLoggedInUser(){
        return loggedInUser;
    }

    public Boolean hasAnyUserLoggedIn() {
        if (loggedInUser == null)
            return false;
        return true;
    }

    public void purchase() throws Exception {
        loggedInUser.completePurchase();
    }

    public void addDiscount(List<Discount> discounts) {
        for(User user: users.values()){
            for (Discount discount : discounts)
                user.addDiscount(discount);
        }
    }
}