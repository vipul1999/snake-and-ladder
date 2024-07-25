package com.game.design;

import com.game.design.database.entity.Player;
import com.game.design.database.entity.SnakesAndLadder;
import com.game.design.services.GameFactory;
import com.game.design.utils.GeneratorUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DesignApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testDefaultConstructor() {
		SnakesAndLadder game = GameFactory.createGame(1);
		assertNotNull(game.getSnakes());
		assertNotNull(game.getLadders());
		assertNotNull(game.getPlayers());
		assertTrue(game.getPlayers().size() > 0);
	}
	@Test
	void testParameterizedConstructor() {
		Map<Integer, Integer> snakes = new HashMap<>();
		snakes.put(16, 6);
		Map<Integer, Integer> ladders = new HashMap<>();
		ladders.put(2, 20);
		List<Player> players = Arrays.asList(new Player("Alice", 1), new Player("Bob", 1));

		SnakesAndLadder game = new SnakesAndLadder(snakes, ladders, players);

		assertEquals(snakes, game.getSnakes());
		assertEquals(ladders, game.getLadders());
		assertEquals(players, game.getPlayers());
	}

	@Test
	void testFileBasedConstructor() throws IOException {
		File file = new File("test_data.txt");
		// Create file with test data
		try (PrintWriter writer = new PrintWriter(file)) {
			writer.println("1");
			writer.println("16 6");
			writer.println("1");
			writer.println("2 20");
			writer.println("2");
			writer.println("Alice 1");
			writer.println("Bob 1");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		SnakesAndLadder game = new SnakesAndLadder(file);
		assertEquals(1, game.getSnakes().size());
		assertEquals(1, game.getLadders().size());
		assertEquals(2, game.getPlayers().size());

		file.delete();
	}

	@Test
	void MannualOverRide(){
		//here will be list of integers which will represent location of each player
		SnakesAndLadder snakesAndLadder = new SnakesAndLadder();

		List<Integer> playerLocations = new ArrayList<>(Arrays.asList(1,2,3,5));
		List<Player> players = GeneratorUtil.generatePlayersByInitialPosition(playerLocations);
		//number of dices are two here
		Queue<List<Integer>> diceRollValue = new LinkedList<>(Stream.of(
				Arrays.asList(4, 5),
				Arrays.asList(1, 2),
				Arrays.asList(3, 4),
				Arrays.asList(5, 6),
				Arrays.asList(4, 5),
				Arrays.asList(1, 2),
				Arrays.asList(3, 4),
				Arrays.asList(5, 6)
		).toList());
		snakesAndLadder.setDiceRollsQueue(diceRollValue);
		snakesAndLadder.setPlayers(players);
		//final location of all players
		for(int i=0;i< diceRollValue.size();i++){
			snakesAndLadder.nextTurn(0);
		}
		for(Player player : players){
			System.out.println(player.getName() + " " + player.getPosition());
		}
	}



}
