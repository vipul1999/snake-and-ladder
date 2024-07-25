package com.game.design.constants;

import com.game.design.enums.MovementStrategy;

public class GameConstants {
    public static final int GAME_VERSION = 2;
    public static final int BOARD_SIZE = 100; // Standard board size for Snakes and Ladders
    public static final int GRID_SIZE = 10;
    public static final int MINE_STUCK_COUNT = 2;
    public static final int CROCODILE_BITE_SETBACK = 5;
    public static final int NUMBER_OF_DICES = 2;
    public static final int SIDES_IN_A_DICE = 6;
    public static final int NUMBER_OF_SNAKE = 5;
    public static final int NUMBER_OF_LADDERS = 10;
    public static final int NUMBER_OF_PLAYERS = 4;
    public static final MovementStrategy MOVEMENT_STRATEGY = MovementStrategy.SUM;
}
