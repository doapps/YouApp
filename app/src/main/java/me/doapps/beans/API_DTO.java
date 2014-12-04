package me.doapps.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gantz on 3/12/14.
 */
public class API_DTO {

    private JSONObject dataSource;

    private String apiVersion = "apiVersion";

    public void setDataSource(JSONObject dataSource) {
        this.dataSource = dataSource;
    }

    public JSONObject getDataSource() {
        return dataSource;
    }

    public String getApiVersion() {
        return parseString(apiVersion,this.dataSource);
    }

    public Data_DTO getData_dto() {
        return parseDataDto("data",this.dataSource);
    }

    /**
     * Methods parse JSON
     */
    public String parseString(String key,JSONObject dataSource) {
        try {
            if(!dataSource.isNull(key)){
                return dataSource.getString(key);
            }else{
                return "NULL";
            }
        }catch (JSONException e){
            e.printStackTrace();
            return "NULL";
        }
    }

    public int parseInt(String key,JSONObject dataSource) {
        try {
            if(!dataSource.isNull(key)){
                return dataSource.getInt(key);
            }else{
                return -1;
            }
        }catch (JSONException e){
            e.printStackTrace();
            return -1;
        }
    }

    public JSONObject parseJSON(String key,JSONObject dataSource) {
        try {
            if(!dataSource.isNull(key)){
                return dataSource.getJSONObject(key);
            }else{
                return null;
            }
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public JSONArray parseJSONArray(String key,JSONObject dataSource) {
        try {
            if(!dataSource.isNull(key)){
                return dataSource.getJSONArray(key);
            }else{
                return null;
            }
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public Data_DTO parseDataDto(String data,JSONObject dataSource){
        try {
            if(!dataSource.isNull(data)){
                Data_DTO data_dto = new Data_DTO();
                data_dto.setDataSource(dataSource.getJSONObject(data));
                return data_dto;
            }else{
                return null;
            }
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
