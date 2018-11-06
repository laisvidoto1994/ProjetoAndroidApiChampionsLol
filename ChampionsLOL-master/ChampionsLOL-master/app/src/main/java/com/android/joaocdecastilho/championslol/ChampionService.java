package com.android.joaocdecastilho.championslol;


import com.android.joaocdecastilho.championslol.models.Champion;
import com.android.joaocdecastilho.championslol.models.ChampionCatalog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ChampionService {

    public static final String BASE_URL = "https://raw.githubusercontent.com/ngryman/lol-champions/master/";

    @GET("champions.json")
    Call<List<Champion>> listChampion();
}
