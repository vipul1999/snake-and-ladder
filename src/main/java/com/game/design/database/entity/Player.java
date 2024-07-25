package com.game.design.database.entity;

import lombok.Data;

@Data
public class Player {
    String name;
    int position;

    public Player(String name, int position) {
        this.name = name;
        this.position = position;
    }
}
