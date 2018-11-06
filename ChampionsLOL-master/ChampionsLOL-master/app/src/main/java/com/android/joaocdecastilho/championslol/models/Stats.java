package com.android.joaocdecastilho.championslol.models;

/**
 * Created by Laís Vidoto on 27/10/2018.
 */

public class Stats {

    public double hp;
    public double mp;

    public Stats(double hp, double mp) {
        this.hp = hp;
        this.mp = mp;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getMp() {
        return mp;
    }

    public void setMp(double mp) {
        this.mp = mp;
    }
}
