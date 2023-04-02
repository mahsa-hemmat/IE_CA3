package baloot.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Comment {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String userEmail;
    private int commodityId;
    private String text;
    private String date;
    private int like = 0;

    public String getDate() {
        return date;
    }
    private int dislike = 0;
    //private Map<String, Integer> votes = new HashMap<>();;
    public Comment(){};
    public Comment(String userEmail, int commodityId, String text, String date){
        this.userEmail = userEmail;
        this.commodityId = commodityId;
        this.text = text;
        this.date = date;
    }
    public void addLikeDislike(int vote){
        if(vote == 1)
            like += 1;
        else
            dislike += 1;
    }

    public String getUserEmail() {
        return userEmail;
    }
    public Integer getcommodityId() {
        return commodityId;
    }
    public String getText() {
        return text;
    }
    public int getLike() {
        return like;
    }
    public int getDislike() {
        return dislike;
    }
}
