package com.game.design.services;

import com.game.design.database.entity.SnakesAndLadder;

public interface Playable {
    void playGame();
    void printBoard(SnakesAndLadder snakesAndLadder);
}
