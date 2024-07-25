package com.game.design;

import com.game.design.database.entity.Player;
import com.game.design.database.entity.SnakesAndLadder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GamePlayTest {

    @Test
    void testPlayerMovement() {
        Map<Integer, Integer> snakes = new HashMap<>();
        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(3, 22);
        List<Player> players = List.of(new Player("Alice", 1));

        SnakesAndLadder game = new SnakesAndLadder(snakes, ladders, players);

        // Simulate dice rolls
        Queue<List<Integer>> diceRollsQueue = new LinkedList<>();
        diceRollsQueue.add(Arrays.asList(1,1)); // Alice rolls a 2
        game.setDiceRollsQueue(diceRollsQueue);

        game.nextTurn(0);
        assertEquals(22, game.getPlayers().get(0).getPosition()); // Should land on 3 and climb ladder to 22
    }

    @Test
    void testSnakeEncounter() {
        Map<Integer, Integer> snakes = new HashMap<>();
        snakes.put(14, 4);
        List<Player> players = Arrays.asList(new Player("Bob", 10));

        SnakesAndLadder game = new SnakesAndLadder(snakes, new HashMap<>(), players);

        // Simulate dice rolls
        Queue<List<Integer>> diceRollsQueue = new LinkedList<>();
        diceRollsQueue.add(Arrays.asList(2,2)); // Bob rolls a 4
        game.setDiceRollsQueue(diceRollsQueue);

        game.nextTurn(0);
        assertEquals(4, game.getPlayers().get(0).getPosition()); // Should be moved to 4 by snake
    }

    @Test
    void testLadderEncounter() {
        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(5, 15);
        List<Player> players = Arrays.asList(new Player("Alice", 3));

        SnakesAndLadder game = new SnakesAndLadder(new HashMap<>(), ladders, players);

        // Simulate dice rolls
        Queue<List<Integer>> diceRollsQueue = new LinkedList<>();
        diceRollsQueue.add(Arrays.asList(1,1)); // Alice rolls a 2
        game.setDiceRollsQueue(diceRollsQueue);

        game.nextTurn(0);
        assertEquals(15, game.getPlayers().get(0).getPosition()); // Should climb the ladder to 15
    }

    @Test
    void testPlayerStartingAgain() {
        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(2, 20);
        List<Player> players = Arrays.asList(
                new Player("Alice", 1),
                new Player("Bob", 20)
        );

        SnakesAndLadder game = new SnakesAndLadder(new HashMap<>(), ladders, players);

        // Simulate dice rolls
        Queue<List<Integer>> diceRollsQueue = new LinkedList<>();
        diceRollsQueue.add(Arrays.asList(18,1)); // Alice rolls a 19 and lands on 20
        diceRollsQueue.add(Arrays.asList(1,2)); // Bob rolls 3
        game.setDiceRollsQueue(diceRollsQueue);
        game.nextTurn(0);
        assertEquals(4, game.getPlayers().get(1).getPosition()); // Bob should move back to start
    }

    @Test
    void testMultiplePlayersWinning() {
        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(98, 100); // Ladder that takes player to 100
        List<Player> players = Arrays.asList(
                new Player("Alice", 97),
                new Player("Bob", 99)
        );

        SnakesAndLadder game = new SnakesAndLadder(new HashMap<>(), ladders, players);

        // Simulate dice rolls
        Queue<List<Integer>> diceRollsQueue = new LinkedList<>();
        diceRollsQueue.add(Arrays.asList(3)); // Alice rolls a 3 (moves to 100)
        diceRollsQueue.add(Arrays.asList(1)); // Bob rolls a 1 (moves to 100)
        game.setDiceRollsQueue(diceRollsQueue);

        assertTrue(game.nextTurn(0)); // Both should win, so game should return true
    }





}
