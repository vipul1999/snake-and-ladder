package com.game.design.utils;

import com.game.design.database.entity.Player;
import com.game.design.database.entity.SnakesAndLadder;
//import com.game.design.database.entity.SnakesAndLadderV2;

import java.util.*;

import static com.game.design.constants.GameConstants.*;

public class GeneratorUtil {

    public static final Random rand = new Random();

    public static Map<Integer, Integer> generateRandomSnakes() {
        Map<Integer, Integer> snakes = new HashMap<>();
//        int numSnakes = rand.nextInt(10) + 5; // Random number of snakes between 5 and 15
        int numSnakes = NUMBER_OF_SNAKE;
        while (snakes.size() < numSnakes) {
            // Generate a random head position
            int head = rand.nextInt(GRID_SIZE * GRID_SIZE - 1) + 1; // Head between 1 and 99

            // Generate a valid tail position that is below the head
            int tail = rand.nextInt(((head-1)/10)*10+1)-1;

            if (tail>0 && tail < head && !snakes.containsKey(head) && !snakes.containsValue(tail)) {
                snakes.put(head, tail);
            }
        }
        return snakes;
    }

    public static Map<Integer, Integer> generateRandomLadders(Map<Integer, Integer> existingSnakes) {
        Map<Integer, Integer> ladders = new HashMap<>();
//        int numLadders = rand.nextInt(10) + 5; // Random number of ladders between 5 and 15
        int numLadders = NUMBER_OF_LADDERS;
        while (ladders.size() < numLadders) {
            int bottom = rand.nextInt(BOARD_SIZE - 1) + 1; // Bottom between 1 and 99
//            int top = rand.nextInt(BOARD_SIZE - bottom) + bottom + 10; // Top greater than bottom
            int top = rand.nextInt(BOARD_SIZE-1) + (((bottom-1)/10)+1)*10 + 1;
            if (top<BOARD_SIZE && !ladders.containsKey(bottom) && !ladders.containsValue(top) && !existingSnakes.containsKey(bottom)) {
                ladders.put(bottom, top);
            }
        }
        return ladders;
    }

    public static Map<Integer, Integer> generateRandomMines(
            Map<Integer, Integer> snakes,
            Map<Integer, Integer> crocodiles,
            Map<Integer, Integer> ladders
    ) {
        Map<Integer, Integer> mines = new HashMap<>();
        int numMines = rand.nextInt(5) + 3; // Random number of mines between 3 and 8

        while (mines.size() < numMines) {
            int position = rand.nextInt(GRID_SIZE * GRID_SIZE - 1) + 1;
            if (!mines.containsKey(position)
                    && !snakes.containsKey(position)
                    && !crocodiles.containsKey(position)
                    && !ladders.containsKey(position)) {
                mines.put(position, position);
            }
        }

        return mines;
    }

    public static Map<Integer, Integer> generateRandomCrocodiles(
            Map<Integer, Integer> snakes,
            Map<Integer, Integer> ladders) {
        Map<Integer, Integer> crocodiles = new HashMap<>();
        int numCrocodiles = rand.nextInt(5) + 3; // Random number of crocodiles between 3 and 8

        while (crocodiles.size() < numCrocodiles) {
            int position = rand.nextInt(GRID_SIZE * GRID_SIZE - 1) + 5;
            if (position<BOARD_SIZE && !crocodiles.containsKey(position) && !snakes.containsKey(position) && !ladders.containsKey(position)) {
                crocodiles.put(position, position - CROCODILE_BITE_SETBACK);
            }
        }
        return crocodiles;
    }

    public static List<Player> generatePlayers() {
        List<Player> players = new ArrayList<>();
//        int numPlayers = rand.nextInt(4) + 2; // Random number of players between 2 and 5
        int numPlayers = NUMBER_OF_PLAYERS;
        for (int i = 0; i < numPlayers; i++) {
            String name = "Player" + (i + 1);
            int startingPosition = 1; // Players start at position 1

            players.add(new Player(name, startingPosition));
        }
        return players;
    }

    public static List<Player> generatePlayersByInitialPosition(List<Integer> locations){
        List<Player> players = new ArrayList<>();
        for(int i=0;i<locations.size();i++){
            String name = "Player" + (i + 1);
            int startingPosition = 1; // Players start at position 1
            players.add(new Player(name, startingPosition));
        }
        return players;
    }

    public static SnakesAndLadder generateRandomGame() {
        Map<Integer, Integer> snakes = generateRandomSnakes();
        Map<Integer, Integer> ladders = generateRandomLadders(snakes);
        List<Player> players = generatePlayers();

        return new SnakesAndLadder(snakes, ladders, players);
    }
    public static SnakesAndLadder generateRandomGameV2(){
        return new SnakesAndLadder();
    }


}
