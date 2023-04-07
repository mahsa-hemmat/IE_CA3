package baloot.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Comment {
    private UUID id = UUID.randomUUID();
    private String userEmail;
    private int commodityId;
    private String text;
    private String date;
    private int like = 0;
    private int dislike = 0;

    public String getDate() {
        return date;
    }
    public Comment(){};
    public Comment(String userEmail, int commodityId, String text, String date){
        this.userEmail = userEmail;
        this.commodityId = commodityId;
        this.text = text;
        this.date = date;
    }

    public UUID getId() {
        return id;
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
    public Integer getCommodityId() {
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
