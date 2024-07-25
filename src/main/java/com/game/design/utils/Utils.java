package com.game.design.utils;

import com.game.design.enums.MovementStrategy;

import java.util.*;

import static com.game.design.constants.GameConstants.*;

public class Utils {

    public static final Random rand = new Random();

    public static void placeObject(Map<Integer,Integer> objectMap, String[][] board, Character identifier){
        for (Map.Entry<Integer, Integer> snake : objectMap.entrySet()) {
            int start = snake.getKey();
            int end = snake.getValue();
            int startRow = GRID_SIZE - 1 - (start - 1) / GRID_SIZE;
            int startCol = (start - 1) % GRID_SIZE;
            board[startRow][startCol] = identifier +"(" + start + "," + end + ")";
        }
    }
    public static void initializeBoard(String[][] board){
        for (int i = 0; i < GRID_SIZE * GRID_SIZE; i++) {
            int row = GRID_SIZE - 1 - i / GRID_SIZE;
            int col = (i % GRID_SIZE);
            board[row][col] = String.format("%02d", i + 1);
        }
    }


    public static int rollDice(Queue<List<Integer>> diceRollsQueue, MovementStrategy strategy) {
       List<Integer> rolls = new ArrayList<>();
        if (diceRollsQueue != null && !diceRollsQueue.isEmpty()) {
            rolls = diceRollsQueue.poll();
        }
        else {
            for (int i = 0; i < NUMBER_OF_DICES; i++) {
                int roll = rand.nextInt(SIDES_IN_A_DICE) + 1; // Random number between 1 and 6
                rolls.add(roll);
            }
        }

        // Apply the strategy
        switch (strategy) {
            case SUM:
                return rolls.stream().mapToInt(Integer::intValue).sum();
            case MAX:
                return rolls.stream().mapToInt(Integer::intValue).max().orElseThrow();
            case MIN:
                return rolls.stream().mapToInt(Integer::intValue).min().orElseThrow();
            default:
                throw new IllegalArgumentException("Invalid strategy: " + strategy);
        }
    }

    public static void showObjects(Map<Integer,Integer> map, Character c){
        for (Map.Entry<Integer, Integer> snake : map.entrySet()) {
            int head = snake.getKey();
            int tail = snake.getValue();
            System.out.println(c + "(" + head + "," + tail + ")");
        }
    }


    public static void printBoard(String[][] board){
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = GRID_SIZE - 1; col >= 0; col--) {
                String cell = board[row][col];
                System.out.print(String.format("%-10s", cell));

            }
            System.out.println();
        }
    }
}
