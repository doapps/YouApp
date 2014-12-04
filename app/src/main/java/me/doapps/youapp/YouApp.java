package me.doapps.youapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import me.doapps.beans.API_DTO;
import me.doapps.beans.Channel_DTO;
import me.doapps.datasource.Channel_Datasource;
import me.doapps.datasource.Video_DataSource;
import me.doapps.fragments.Fragment_Menu;
import me.doapps.fragments.Fragment_Video;


public class YouApp extends ActionBarActivity {

    private Fragment_Menu mFragmentMenu;
    private CharSequence mTitle;
    private Video_DataSource video_dataSource;

    private API_DTO api_dto;
    private Channel_DTO channel_dto;
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youapp);
        mFragmentMenu = (Fragment_Menu)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mFragmentMenu.setUp(R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));

        /**
         *
         */
        setChannel_dto(((Channel_DTO) new Channel_Datasource().getAdapterChannel(this).getItem(0)));
        getSupportFragmentManager().beginTransaction().replace(R.id.container, Fragment_Video.newInstance(),Fragment_Video.class.getName()).commit();

        /**
         *
         */
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId("ca-app-pub-8995045147204986/6753137354");
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitial.loadAd(adRequest);

        /**
         *
         */
        AdView adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequestb= new AdRequest.Builder().build();
        adView.loadAd(adRequestb);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.youapp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/search?q=cristian%20alex%20palomino%20rivera");
            startActivity(Intent.createChooser(intent, "Compartir"));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    public void setChannel_dto(Channel_DTO channel_dto) {
        this.channel_dto = channel_dto;
    }

    public Channel_DTO getChannel_dto() {
        return channel_dto;
    }

    public API_DTO getApi_dto() {
        return api_dto;
    }

    public void setApi_dto(API_DTO api_dto) {
        this.api_dto = api_dto;
    }

    public InterstitialAd getInterstitial() {
        return interstitial;
    }

    @Override
    public void onBackPressed() {
        interstitial.show();
        super.onBackPressed();
    }
}
