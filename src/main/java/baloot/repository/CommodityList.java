package baloot.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import baloot.exception.*;

import java.util.*;

public class CommodityList {
    Map<Integer, Commodity> commodities;

    public CommodityList() {commodities = new HashMap<>();}
    public Commodity getCommodityById(int id) throws Exception{
        if(!(commodities.containsKey(id)))
            throw new CommodityNotFoundException(id);
        return commodities.get(id);
    }

    public void addCommodity(Commodity co) throws Exception{
            if(!(commodities.containsKey(co.getId())))
                commodities.put(co.getId(), co);
    }

    public List<Commodity> getList() throws Exception{
        return new ArrayList<Commodity>(commodities.values());
    }

    public List<Commodity> filterByCategory(ArrayList<String> cat) throws Exception{
        List<Commodity> list= new ArrayList<Commodity>();
        for(Commodity co : commodities.values())
            if(co.getCategories().equals(cat))
                list.add(co);
        return list;
    }
    
    public boolean hasCommodity(int id){
        return (commodities.containsKey(id));
    }

    public void voteComment(String id, int vote) throws Exception {
        for(Commodity commodity: commodities.values()){
            if(commodity.getComments().containsKey(Integer.parseInt(id))){
                commodity.getComments().get(Integer.parseInt(id)).addLikeDislike(vote);
                break;
            }
        }
        throw new CommandNotFoundException("id");
    }

    public List<Commodity> filterByPrice(int start, int end) {
        List<Commodity> list= new ArrayList<Commodity>();
        for(Commodity co : commodities.values())
            if((co.getPrice()>=start) && (co.getPrice()<end))
                list.add(co);
        return list;
    }
}
