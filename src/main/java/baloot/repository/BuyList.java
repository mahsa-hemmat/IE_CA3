package baloot.repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import baloot.exception.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyList {
    private Map<Integer, Commodity> commodities;
    int discount = 0;

    public Map<Integer, Commodity> getCommodities() {
        return commodities;
    }

    public BuyList(){
        commodities = new HashMap<>();
    }
    public void addCommodity(Commodity commodity) throws Exception{
        if(commodities.containsKey(commodity.getId()))
            throw new InValidInputException("Commodity is already in the user Buylist.");
        commodities.put(commodity.getId() ,commodity);
    }

    public void removeCommodity(int id) throws Exception{
        if(!(commodities.containsKey(id)))
            throw new CommodityNotFoundException(id);
        commodities.remove(id);
    }

    public List<Commodity> printList() throws Exception{
        return new ArrayList<Commodity>(commodities.values());
    }

    public int getTotalCost() {
        int totalPrice = 0;
        for(Commodity co:commodities.values()){
            totalPrice += co.getPrice();
        }
        totalPrice -= (discount/100) * totalPrice;
        return totalPrice;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
