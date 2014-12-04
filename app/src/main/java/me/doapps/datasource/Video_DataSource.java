package me.doapps.datasource;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.doapps.adapters.Adapter_Video;
import me.doapps.beans.API_DTO;
import me.doapps.beans.Item_DTO;
import me.doapps.youapp.YouApp;

/**
 * Created by Gantz on 3/12/14.
 */
public class Video_DataSource {

    private Context context;
    private Interface_Video interface_video;

    public Video_DataSource(Context context) {
        this.context = context;
    }

    public void getVideos(String idCanal,int minimo, int maximo) {
        String urlVacio = "https://gdata.youtube.com/feeds/api/videos";
        String canal = "?author=" + idCanal;
        String versionApi = "&v=2.1";
        String ordeandoPor = "&orderby=updated";
        String formato = "&alt=jsonc";
        String desde = "&start-index=" + String.valueOf(minimo);
        String cantidadResultados = "&max-results=" + String.valueOf(maximo);

        String URL = urlVacio + canal + versionApi + ordeandoPor + formato + desde + cantidadResultados;

        if (isOnline()) {
            RequestQueue queue = Volley.newRequestQueue(context);
            StringRequest mRequest = new StringRequest(Request.Method.GET,
                    URL,
                    mResponseListener,
                    mErrorListener);
            queue.add(mRequest);
        } else {
            Toast.makeText(context, "Debes estar conectado a Internet...", Toast.LENGTH_SHORT).show();
        }
    }

    Response.Listener<String> mResponseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject mObject = new JSONObject(response);
                API_DTO api_dto = new API_DTO();
                api_dto.setDataSource(mObject);

                interface_video.onFinishLoad(new Adapter_Video(context,api_dto.getData_dto().getItem_dtos()),api_dto.getData_dto().getItem_dtos());


            } catch (JSONException e) {
                Toast.makeText(context, "Ocurrio un error, intentelo nuevamente...", Toast.LENGTH_SHORT).show();
            }
        }
    };

    Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError e) {
            Toast.makeText(context, "Ocurrio un error, intentelo nuevamente...", Toast.LENGTH_SHORT).show();
        }
    };


    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public void setInterface_video(Interface_Video interface_video) {
        this.interface_video = interface_video;
    }

    public interface Interface_Video {
        void onFinishLoad(Adapter_Video adapter_video,ArrayList<Item_DTO> videos);
    }
}
