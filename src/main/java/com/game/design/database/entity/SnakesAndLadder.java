package com.game.design.database.entity;

import com.game.design.constants.GameConstants;
import com.game.design.enums.MovementStrategy;
import com.game.design.services.Playable;
import com.game.design.utils.Utils;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.game.design.constants.GameConstants.*;
import static com.game.design.utils.GeneratorUtil.*;

@Data
public class SnakesAndLadder implements Playable {
    private Map<Integer, Integer> snakes = new HashMap<>();
    private Map<Integer, Integer> ladders = new HashMap<>();
    private Queue<List<Integer>> diceRollsQueue;
    private List<Player> players = new ArrayList<>();
    private Map<Integer,Integer> mines = new HashMap<>();
    private Map<Integer,Integer> crocodiles = new HashMap<>();

    public Random rand = new Random();

    public SnakesAndLadder(){
        this.snakes = generateRandomSnakes();
        this. ladders = generateRandomLadders(snakes);
        this.players = generatePlayers();
        this.crocodiles = generateRandomCrocodiles(snakes,ladders);
        this.mines = generateRandomMines(snakes,crocodiles,ladders);
    }

    public SnakesAndLadder(Map<Integer, Integer> snakes,
                           Map<Integer, Integer> ladders,
                           List<Player> players) {
        this.snakes = snakes;
        this.ladders = ladders;
        this.players = players;
    }

    public SnakesAndLadder(File file) throws IOException {
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        this.players = new ArrayList<>();
        this.rand = new Random();
        // Check if file exists
        if (!file.exists()) {
            throw new IOException("File not found: " + file.getAbsolutePath());
        }

        try (Scanner scanner = new Scanner(file)) {
            // Reading Snakes
            int numSnakes = scanner.nextInt();
            for (int i = 0; i < numSnakes; i++) {
                int head = scanner.nextInt();
                int tail = scanner.nextInt();
                snakes.put(head, tail);
            }

            // Reading Ladders
            int numLadders = scanner.nextInt();
            for (int i = 0; i < numLadders; i++) {
                int bottom = scanner.nextInt();
                int top = scanner.nextInt();
                ladders.put(bottom, top);
            }

            // Reading Players
            int numPlayers = scanner.nextInt();
            for (int i = 0; i < numPlayers; i++) {
                String name = scanner.next();
                int startingPosition = scanner.nextInt();
                players.add(new Player(name, startingPosition));
            }
        }
    }
    public void playGame() {
        printBoard(this);
        int stuck_count = MINE_STUCK_COUNT;
        while (true) {
            if(nextTurn(stuck_count)){
                break;
            }
        }
    }

    public boolean nextTurn(int stuck_count){
        for (Player player : this.getPlayers()) {
            int diceRoll = Utils.rollDice(this.getDiceRollsQueue(), MOVEMENT_STRATEGY);
            int oldPosition = player.position;
            int newPosition = player.position + diceRoll;

            if (newPosition > BOARD_SIZE) {
                System.out.println(player.name + " rolled a " + diceRoll + " but needs to roll " + (BOARD_SIZE - player.position) + " or less to win.");
                continue; // Player's move is invalid, so skip
            }

            if (!CollectionUtils.isEmpty(this.getSnakes()) && this.getSnakes().containsKey(newPosition)) {
                System.out.println(player.name + " rolled a " + diceRoll + " and bitten by snake at " + newPosition + " and moved from " + newPosition + " to " + this.getSnakes().get(newPosition));
                player.position = this.getSnakes().get(newPosition);
            } else if(!CollectionUtils.isEmpty(this.getCrocodiles()) && this.getCrocodiles().containsKey(newPosition)){
                System.out.println(player.name + " rolled a " + diceRoll + " and bitten by crocodiles at " + newPosition + " and moved from " +newPosition + " to " + this.getCrocodiles().get(newPosition));
                player.position = this.getCrocodiles().get(newPosition);
            }
            else if (!CollectionUtils.isEmpty(this.getLadders()) && this.getLadders().containsKey(newPosition)) {
                System.out.println(player.name + " rolled a " + diceRoll + " and climbed the ladder at " + newPosition + " moved from " + newPosition + " to " + this.getLadders().get(newPosition));
                player.position = this.getLadders().get(newPosition);
            } else if(!CollectionUtils.isEmpty(this.getMines()) && this.getMines().containsKey(newPosition)){
                if(stuck_count==0){
                    player.position = newPosition;
                } else if(stuck_count>0){
                    System.out.println(player.name + " stuck at position "+ player.position);
                    stuck_count--;
                }
            } else {
                player.position = newPosition;
                System.out.println(player.name + " rolled a " + diceRoll + " and moved from " + oldPosition + " to " + player.position);
            }

            // Find the player who was previously on this position
            for (Player p : this.getPlayers()) {
                if (!Objects.equals(p.name, player.name) && p.position == player.position) {
                    System.out.println(p.name + " was present of the new position of " + player.name);
                    p.position = 1; // Move the previous player back to start
                    System.out.println(p.name + " has been moved back to start.");
                    break;
                }
            }

            if (player.position == BOARD_SIZE) {
                System.out.println(player.name + " wins the game!");
                return true;
            }
        }
        return false;
    }



    public void printBoard(SnakesAndLadder game) {
        System.out.println("Snakes and Ladders Board :");
        String[][] board = new String[GRID_SIZE][GRID_SIZE];
        Utils.initializeBoard(board);
        if(!CollectionUtils.isEmpty(game.getSnakes())){
            Utils.placeObject(game.getSnakes(),board,'S');
        }
        if(!CollectionUtils.isEmpty(game.getLadders())){
            Utils.placeObject(game.getLadders(),board,'L');
        }
        if(!CollectionUtils.isEmpty(game.getCrocodiles())){
            Utils.placeObject(game.getCrocodiles(),board,'C');
        }
        if(!CollectionUtils.isEmpty(game.getMines())){
            Utils.placeObject(game.getMines(),board,'M');
        }
        Utils.printBoard(board);
    }

}
