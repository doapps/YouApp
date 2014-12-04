package me.doapps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import me.doapps.beans.Channel_DTO;
import me.doapps.utils.Util_Fonts;
import me.doapps.youapp.R;

/**
 * Created by Gantz on 26/05/14.
 */
public class Adapter_Channel extends BaseAdapter {

    protected ArrayList<Channel_DTO> channel_dtos;
    protected Context context;
    private LayoutInflater layoutInflater;

    public Adapter_Channel(Context context, ArrayList<Channel_DTO> channel_dtos) {
        super();
        this.context = context;
        this.channel_dtos = channel_dtos;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return channel_dtos.size();
    }

    @Override
    public Object getItem(int position) {
        return channel_dtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;
        Channel_DTO channel_dto = channel_dtos.get(position);

        if (channel_dto != null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.item_menu,null);

            holder.container = (LinearLayout) convertView.findViewById(R.id.item_container);
            holder.nombre = (TextView) convertView.findViewById(R.id.nombre);
            holder.imagen = (ImageView) convertView.findViewById(R.id.image);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.imagen.setImageResource(channel_dto.getImagenDrawable());
        holder.nombre.setText(channel_dto.getNombre());
        holder.container.setBackgroundResource(channel_dto.getColorResource());

        /**
         * Fonts
         */
        holder.nombre.setTypeface(Util_Fonts.setRobotoMedium(context));

        return convertView;
    }

    static class Holder {
        LinearLayout container;
        TextView nombre;
        ImageView imagen;
    }
}
