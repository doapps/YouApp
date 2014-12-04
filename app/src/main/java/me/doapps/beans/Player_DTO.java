package me.doapps.beans;

/**
 * Created by Gantz on 3/12/14.
 */
public class Player_DTO extends Item_DTO {

    private String mdefault = "default";
    private String mobile = "mobile";

    public String getMdefault() {
        return parseString(mdefault,getDataSource());
    }

    public String getMobile() {
        return parseString(mobile,getDataSource());
    }

}
