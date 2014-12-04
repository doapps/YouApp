package me.doapps.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 3/12/14.
 */
public class Data_DTO extends API_DTO {

    private String updated = "updated";
    private String totalItems = "totalItems";
    private String startIndex = "startIndex";
    private String itemsPerPage = "itemsPerPage";

    public String getUpdated() {
        return parseString(updated, getDataSource());
    }

    public int getItemsPerPage() {
        return parseInt(itemsPerPage, getDataSource());
    }

    public int getStartIndex() {
        return parseInt(startIndex, getDataSource());
    }

    public int getTotalItems() {
        return parseInt(totalItems, getDataSource());
    }

    public ArrayList<Item_DTO> getItem_dtos() throws JSONException {
        ArrayList<Item_DTO> item_dtos = new ArrayList<Item_DTO>();
        JSONArray items_array = getDataSource().getJSONArray("items");
        for (int i = 0; i < items_array.length(); i++) {
            Item_DTO item_dto = new Item_DTO();
            item_dto.setDataSource(items_array.getJSONObject(i));
            item_dtos.add(item_dto);
        }
        return item_dtos;
    }
}
