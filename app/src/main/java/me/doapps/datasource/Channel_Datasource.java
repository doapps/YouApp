package me.doapps.datasource;

import android.content.Context;

import java.util.ArrayList;

import me.doapps.adapters.Adapter_Channel;
import me.doapps.beans.Channel_DTO;
import me.doapps.youapp.R;

/**
 * Created by Gantz on 3/12/14.
 */
public class Channel_Datasource {

    public Adapter_Channel getAdapterChannel(Context context){
        Adapter_Channel adapter_channel = new Adapter_Channel(context,getChannels());
        return adapter_channel;
    }

    private ArrayList<Channel_DTO> getChannels(){

        Channel_DTO doappsme = new Channel_DTO("doappsme","#FFFFFF",android.R.color.white,android.R.drawable.ic_menu_directions,"","DoApps");
        Channel_DTO trucoteca = new Channel_DTO("trucotecaanalisis","#FFFFFF",android.R.color.white,android.R.drawable.ic_menu_directions,"","Trucoteca");
        Channel_DTO worldofwarcraft = new Channel_DTO("WorldofWarcraft","#FFFFFF",android.R.color.white,android.R.drawable.ic_menu_directions,"","World of Warcraft");
        Channel_DTO heartstone = new Channel_DTO("PlayHearthstone","#FFFFFF",android.R.color.white,android.R.drawable.ic_menu_directions,"","Hearthstone");
        Channel_DTO starcraft = new Channel_DTO("starcraft","#FFFFFF",android.R.color.white,android.R.drawable.ic_menu_directions,"","Star Craft");

        ArrayList<Channel_DTO> channel_dtos = new ArrayList<Channel_DTO>();
        channel_dtos.add(doappsme);
        channel_dtos.add(trucoteca);
        channel_dtos.add(worldofwarcraft);
        channel_dtos.add(heartstone);
        channel_dtos.add(starcraft);

        return channel_dtos;
    }
}
