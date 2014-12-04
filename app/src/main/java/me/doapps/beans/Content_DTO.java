package me.doapps.beans;

/**
 * Created by Gantz on 3/12/14.
 */
public class Content_DTO extends Item_DTO{

    private String one = "1";
    private String five = "5";
    private String six = "6";

    public String getOne() {
        return parseString(one,getDataSource());
    }

    public String getFive() {
        return parseString(five,getDataSource());
    }

    public String getSix() {
        return parseString(six,getDataSource());
    }
}
