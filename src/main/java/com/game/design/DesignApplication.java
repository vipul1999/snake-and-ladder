package com.game.design;

import com.game.design.constants.GameConstants;
import com.game.design.database.entity.SnakesAndLadder;
import com.game.design.services.GameFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class DesignApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DesignApplication.class, args);
		File file = null;
		SnakesAndLadder game = new SnakesAndLadder();
		//init by file
		try {
			file = new File("C:\\Users\\vipul\\Downloads\\design\\design\\src\\main\\java\\com\\game\\design\\artifact\\GameConfig.txt");
			if(file.exists()){
				System.out.println("File Found");
				game = new SnakesAndLadder(file);
			}
		} catch (Exception e){
			//do nothing back up plan by random generating
		}

		//init by version
		game = GameFactory.createGame(GameConstants.GAME_VERSION);
		game.playGame();
	}
}
