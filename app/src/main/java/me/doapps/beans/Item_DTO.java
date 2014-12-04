package me.doapps.beans;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gantz on 3/12/14.
 */
public class Item_DTO extends Data_DTO {

    private String id = "id";
    private String uploaded = "uploaded";
    private String updated = "updated";
    private String uploader = "uploader";
    private String category = "category";
    private String title = "title";
    private String description = "description";
    private String duration = "duration";
    private String aspectRatio = "aspectRatio";
    private String rating = "rating";
    private String likeCount = "likeCount";
    private String ratingCount = "ratingCount";
    private String viewCount = "viewCount";
    private String favoriteCount = "favoriteCount";
    private String commnentCount = "commnentCount";

    public int getCommnentCount() {
        return parseInt(commnentCount, getDataSource());
    }

    public int getFavoriteCount() {
        return parseInt(favoriteCount, getDataSource());
    }

    public int getViewCount() {
        return parseInt(viewCount, getDataSource());
    }

    public int getRatingCount() {
        return parseInt(ratingCount, getDataSource());
    }

    public int getRating() {
        return parseInt(rating, getDataSource());
    }

    public String getLikeCount() {
        return parseString(likeCount, getDataSource());
    }

    public String getAspectRatio() {
        return parseString(aspectRatio, getDataSource());
    }

    public int getDuration() {
        return parseInt(duration, getDataSource());
    }

    public String getDescription() {
        return parseString(description, getDataSource());
    }

    public String getTitle() {
        return parseString(title, getDataSource());
    }

    public String getCategory() {
        return parseString(category, getDataSource());
    }

    public String getUploader() {
        return parseString(uploader, getDataSource());
    }

    public String getUpdated() {
        return parseString(updated, getDataSource());
    }

    public String getUploaded() {
        return parseString(uploaded, getDataSource());
    }

    public String getId() {
        return parseString(id, getDataSource());
    }


    /**
     * @return Beans
     */
    public Content_DTO getContent_dto()  {
        try {
            Content_DTO content_dto = new Content_DTO();
            content_dto.setDataSource(getDataSource().getJSONObject("content"));
            return content_dto;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Player_DTO getPlayer_dto()  {
        try {
            Player_DTO player_dto = new Player_DTO();
            player_dto.setDataSource(getDataSource().getJSONObject("player"));
            return player_dto;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Thumbnail_DTO getThumbnail_dto()  {
        try {
            Thumbnail_DTO thumbnail_dto = new Thumbnail_DTO();
            thumbnail_dto.setDataSource(getDataSource().getJSONObject("thumbnail"));
            return thumbnail_dto;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public AccessControl_DTO getAccessControl_dto()  {
        try {
            AccessControl_DTO accessControl_dto = new AccessControl_DTO();
            accessControl_dto.setDataSource(getDataSource().getJSONObject("accessControl"));
            return accessControl_dto;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}


