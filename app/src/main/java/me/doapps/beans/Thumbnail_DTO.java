package me.doapps.beans;

/**
 * Created by Gantz on 3/12/14.
 */
public class Thumbnail_DTO extends Item_DTO {

    private String sqDefault = "sqDefault";
    private String hqDefault = "hqDefault";

    public String getSqDefault() {
        return parseString(sqDefault,getDataSource());
    }

    public String getHqDefault() {
        return parseString(hqDefault,getDataSource());
    }
}
