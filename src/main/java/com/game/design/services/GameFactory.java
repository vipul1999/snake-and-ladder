package com.game.design.services;

import com.game.design.database.entity.Player;
import com.game.design.database.entity.SnakesAndLadder;


import static com.game.design.utils.GeneratorUtil.*;

public class GameFactory {
    public static SnakesAndLadder createGame(int gameVersion){
        if(gameVersion == 1){
            return generateRandomGame();
        } else if(gameVersion == 2){
            return generateRandomGameV2();
        } else{
            return generateRandomGame();
        }
    }
}

