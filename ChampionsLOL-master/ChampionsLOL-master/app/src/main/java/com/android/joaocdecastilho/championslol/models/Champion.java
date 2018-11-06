package com.android.joaocdecastilho.championslol.models;

import java.util.List;

public class Champion
{

    private String key;
    private String name;
    private String title;
    private List<String> tags;
    private Stats stats;
    private String icon;
    private String description;

    public Champion(String key, String name, String title, List<String> tags, Stats stats, String icon, String description) {
        this.key = key;
        this.name = name;
        this.title = title;
        this.tags = tags;
        this.stats = stats;
        this.icon = icon;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
