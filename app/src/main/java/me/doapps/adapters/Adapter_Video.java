package me.doapps.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import me.doapps.beans.Item_DTO;
import me.doapps.utils.Util_Fonts;
import me.doapps.youapp.R;

/**
 * Created by Gantz on 26/05/14.
 */
public class Adapter_Video extends BaseAdapter {

    protected ArrayList<Item_DTO> item_dtos;
    protected Context context;
    private LayoutInflater layoutInflater;

    public Adapter_Video(Context context, ArrayList<Item_DTO> item_dtos) {
        super();
        this.context = context;
        this.item_dtos = item_dtos;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return item_dtos.size();
    }

    @Override
    public Object getItem(int position) {
        return item_dtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Holder holder;
        final Item_DTO item_dto = item_dtos.get(position);

        if (item_dto != null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.item_video,null);

            holder.description = (TextView) convertView.findViewById(R.id.video_description);
            holder.views = (TextView) convertView.findViewById(R.id.views);
            holder.nombre = (TextView) convertView.findViewById(R.id.video_tittle);
            holder.imagen = (ImageView) convertView.findViewById(R.id.video_image);
            holder.compartir = (ImageView) convertView.findViewById(R.id.compartir);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Picasso.with(context).load(item_dto.getThumbnail_dto().getHqDefault()).placeholder(R.drawable.video_placeholder).fit().centerCrop().into(holder.imagen);
        holder.nombre.setText(item_dto.getCategory());
        holder.views.setText("Visto " + item_dto.getViewCount());
        holder.description.setText(item_dto.getTitle());

        holder.compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context,v);
                popup.getMenuInflater().inflate(R.menu.youapp,popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.compartir){
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT,item_dto.getPlayer_dto().getMobile());
                            context.startActivity(Intent.createChooser(intent,"Compartir"));
                        }else{

                        }
                        return true;
                    }
                });
                popup.show();
                /*
                Uri bmpUri = getLocalBitmapUri(holder.imagen);
                if (bmpUri != null) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                    shareIntent.setType("image/*");
                    context.startActivity(Intent.createChooser(shareIntent, "Compartir"));
                } else {}
                */
            }
        });

        /**
         * Fonts
         */
        holder.nombre.setTypeface(Util_Fonts.setRobotoMedium(context));
        holder.views.setTypeface(Util_Fonts.setRobotoBoldItalic(context));
        holder.description.setTypeface(Util_Fonts.setRobotoMedium(context));

        return convertView;
    }

    public void add(Item_DTO lista_dto){
        item_dtos.add(lista_dto);
        notifyDataSetChanged();
    }

    static class Holder {
        TextView description;
        TextView views;
        TextView nombre;
        ImageView imagen;
        ImageView compartir;
    }

    /**
     *
     */
    public Uri getLocalBitmapUri(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        Uri bmpUri = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }
}
