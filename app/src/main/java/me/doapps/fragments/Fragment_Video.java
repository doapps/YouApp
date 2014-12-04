package me.doapps.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONException;

import java.util.ArrayList;

import me.doapps.adapters.Adapter_Video;
import me.doapps.beans.Item_DTO;
import me.doapps.datasource.Video_DataSource;
import me.doapps.youapp.Player;
import me.doapps.youapp.R;
import me.doapps.youapp.YouApp;

/**
 * Created by Gantz on 3/12/14.
 */
public class Fragment_Video extends Fragment implements Video_DataSource.Interface_Video, AbsListView.OnScrollListener{

    private ListView lista_video;
    private Video_DataSource video_dataSource;
    private Adapter_Video adapter_video;
    protected boolean isLoading = false;

    private int MINIMO = 1;
    private int MAXIMO = 10;

    public static final Fragment_Video newInstance(){
        return new Fragment_Video();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        video_dataSource = new Video_DataSource(getActivity());
        video_dataSource.getVideos(((YouApp)getActivity()).getChannel_dto().getId(),MINIMO,MAXIMO);
        video_dataSource.setInterface_video(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView empty = new TextView(getActivity());
        empty.setText("Sin Internet :c");

        lista_video = (ListView)getView().findViewById(R.id.lista_video);
        lista_video.setOnScrollListener(this);
        lista_video.setEmptyView(empty);
        lista_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((YouApp) getActivity()).getInterstitial().show();
                Item_DTO item_dto = (Item_DTO) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), Player.class);
                intent.putExtra("idVideo", item_dto.getId());
                startActivity(intent);
            }
        });
    }

    /**
     * Callback method to be invoked while the list view or grid view is being scrolled. If the
     * view is being scrolled, this method will be called before the next frame of the scroll is
     * rendered. In particular, it will be called before any calls to
     * {@link Adapter#getView(int, android.view.View, android.view.ViewGroup)}.
     *
     * @param view        The view whose scroll state is being reported
     * @param scrollState The current scroll state. One of
     *                    {@link #SCROLL_STATE_TOUCH_SCROLL} or {@link #SCROLL_STATE_IDLE}.
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    /**
     * Callback method to be invoked when the list or grid has been scrolled. This will be
     * called after the scroll has completed
     *
     * @param view             The view whose scroll state is being reported
     * @param firstVisibleItem the index of the first visible cell (ignore if
     *                         visibleItemCount == 0)
     * @param visibleItemCount the number of visible cells
     * @param totalItemCount   the number of items in the list adaptor
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
            if (!isLoading) {
                isLoading = true;
                MINIMO = MINIMO + 10;
                video_dataSource.getVideos(((YouApp)getActivity()).getChannel_dto().getId(),MINIMO,MAXIMO);
                video_dataSource.setInterface_video(this);
            }
        }
    }

    @Override
    public void onFinishLoad(Adapter_Video adapter_video,ArrayList<Item_DTO> videos) {
        if(MINIMO == 1){
            this.adapter_video = adapter_video;
            lista_video.setAdapter(this.adapter_video);
        }else{
            for (int i = 0; i < videos.size(); i++) {
                this.adapter_video.add(videos.get(i));
                isLoading = false;
            }
        }
    }
}
