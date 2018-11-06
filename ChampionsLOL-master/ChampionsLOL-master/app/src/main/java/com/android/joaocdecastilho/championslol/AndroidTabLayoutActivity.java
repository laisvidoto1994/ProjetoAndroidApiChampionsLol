package com.android.joaocdecastilho.championslol;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AndroidTabLayoutActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TabHost tabHost = getTabHost();

        // Tab for Principal
        TabSpec photospec = tabHost.newTabSpec("Principal");
        photospec.setIndicator("Principal", getResources().getDrawable(R.drawable.dashboard_white));
        Intent photosIntent = new Intent(this, ChampionsListActivity.class);
        photospec.setContent(photosIntent);

        // Tab for Favoritos
        TabSpec songspec = tabHost.newTabSpec("Favoritos");
        songspec.setIndicator("Favoritos", getResources().getDrawable(R.drawable.star_rate_white));
        Intent songsIntent = new Intent(this, FavoritosChampionsActivity.class);
        songspec.setContent(songsIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding Principal tab
        tabHost.addTab(songspec); // Adding Favoritos tab
    }
}
