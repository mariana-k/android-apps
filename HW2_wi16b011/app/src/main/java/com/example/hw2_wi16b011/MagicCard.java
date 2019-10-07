package com.example.hw2_wi16b011;

import androidx.annotation.NonNull;
import java.util.LinkedList;
import java.util.List;

public class MagicCard implements Comparable<MagicCard> {
    private String name;
    private String type;
    private List<String> colors;
    private String rarity;

    public MagicCard(String name, String type, String rarity) {
        this.name = name;
        this.type = type;
        this.colors = new LinkedList<>();
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getColors() {
        return colors;
    }

    public void addColor(String color) {
        this.colors.add(color);
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    @Override
    public int compareTo(@NonNull MagicCard second) {
        return name.compareToIgnoreCase(second.name);
    }
}