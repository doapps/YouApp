package me.doapps.beans;

/**
 * Created by Gantz on 3/12/14.
 */
public class AccessControl_DTO extends  Item_DTO {

    private String comment = "comment";
    private String commentVote = "commentVote";
    private String videoRespond = "videoRespond";
    private String rate = "rate";
    private String embed = "embed";
    private String list = "list";
    private String autoPlay = "autoPlay";
    private String syndicate = "syndicate";

    public String getComment() {
        return parseString(comment,getDataSource());
    }

    public String getSyndicate() {
        return parseString(syndicate,getDataSource());
    }

    public String getAutoPlay() {
        return parseString(autoPlay,getDataSource());
    }

    public String getList() {
        return parseString(list,getDataSource());
    }

    public String getEmbed() {
        return parseString(embed,getDataSource());
    }

    public String getRate() {
        return parseString(rate,getDataSource());
    }

    public String getVideoRespond() {
        return parseString(videoRespond,getDataSource());
    }

    public String getCommentVote() {
        return parseString(commentVote,getDataSource());
    }
}
