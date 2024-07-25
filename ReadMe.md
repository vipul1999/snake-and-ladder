Great! Since this is a Maven project, you can include Maven-specific instructions in the README. Here’s an updated version of the README that includes Maven details:

---

# Snakes and Ladders Game

## Overview

This repository contains a Java application for a Snakes and Ladders game. The application uses Spring Boot for setup and initialization and follows the Factory design pattern to create different game configurations based on the version specified.

## Project Structure

- **`com.game.design`**: Contains the core application and game logic.
    - **`DesignApplication.java`**: Entry point of the Spring Boot application.
    - **`services`**: Contains service classes such as `GameFactory` for game creation and `Playable` interface for game functionalities.
    - **`database.entity`**: Contains entity classes such as `SnakesAndLadder`.
    - **`constants`**: Holds game constants such as `GameConstants`.

- **`src/main/resources`**: Contains configuration files, e.g., `application.properties`.

## Features

- **Game Initialization**:
    - Loads game configuration from a file if available.
    - Falls back to default or random game configuration if the file is not found or an error occurs.
- **Version Control**:
    - Supports multiple game versions with different configurations.
    - The game version can be set to choose between different random configurations.

## SnakesAndLadder Class

### Overview

The `SnakesAndLadder` class represents the main game logic and board for the Snakes and Ladders game. It implements the `Playable` interface to handle game functionality such as playing the game and printing the board.

### Key Attributes

- **`snakes`**: A map where the keys represent the starting points of snakes and the values represent the ending points.
- **`ladders`**: A map where the keys represent the bottom of the ladders and the values represent the top.
- **`crocodiles`**: A map where the keys represent the positions of crocodiles and the values represent where players are moved if they land on a crocodile.
- **`mines`**: A map where the keys represent the positions of mines and the values represent the outcome if players land on a mine.
- **`players`**: A list of `Player` objects representing the players in the game.
- **`diceRollsQueue`**: A queue of dice rolls used in the game.
- **`rand`**: A `Random` object used for generating random numbers.

### Constructors

- **`SnakesAndLadder()`**: Initializes the game with randomly generated snakes, ladders, crocodiles, mines, and players.
- **`SnakesAndLadder(Map<Integer, Integer> snakes, Map<Integer, Integer> ladders, List<Player> players)`**: Initializes the game with specified snakes, ladders, and players.
- **`SnakesAndLadder(File file)`**: Initializes the game by reading from a configuration file.

### Methods

- **`playGame()`**: Starts the game and handles turns for all players until one wins. Calls `printBoard()` to display the board at the start.
- **`nextTurn(int stuck_count)`**: Processes the next turn for each player, including handling snakes, ladders, crocodiles, and mines. Manages player positions and detects win conditions.
- **`printBoard(SnakesAndLadder game)`**: Prints the current state of the game board, including snakes, ladders, crocodiles, and mines.

### Example Usage

To play the game, instantiate the `SnakesAndLadder` class and call the `playGame()` method:

```java
SnakesAndLadder game = new SnakesAndLadder();
game.playGame();
```

To load the game configuration from a file:

```java
File configFile = new File("path/to/your/config/file.txt");
SnakesAndLadder game = new SnakesAndLadder(configFile);
game.playGame();
```

## Playable Interface

### Purpose

The `Playable` interface defines the contract for playing a game and printing the board. It ensures that any game class implementing this interface adheres to the methods required for game functionality.

### Methods

- **`playGame()`**: Starts the game and handles the game loop where players take turns.
- **`printBoard(SnakesAndLadder snakesAndLadder)`**: Prints the current state of the game board.

### Example Implementation

Here's how you might implement the `Playable` interface in the `SnakesAndLadder` class:

```java
public class SnakesAndLadder implements Playable {
    // Existing fields and methods...

    @Override
    public void playGame() {
        printBoard(this);
        // Game loop implementation...
    }

    @Override
    public void printBoard(SnakesAndLadder snakesAndLadder) {
        System.out.println("Snakes and Ladders Board:");
        // Board printing implementation...
    }
}
```

## GameFactory

### Purpose

The `GameFactory` class creates instances of `SnakesAndLadder` with different configurations based on the specified game version. It centralizes and standardizes game creation logic.

### Methods

- **`createGame(int gameVersion)`**: Creates and returns a `SnakesAndLadder` instance based on the specified game version. Defaults to a standard game configuration if the version is unsupported.
    - **Parameters**:
        - `gameVersion` (int): The version of the game to create.
    - **Returns**: An instance of `SnakesAndLadder`.

### Example Usage

To create a game with version 1 (standard configuration):

```java
SnakesAndLadder game = GameFactory.createGame(1);
```

To create a game with version 2 (enhanced configuration):

```java
SnakesAndLadders game = GameFactory.createGame(2);
```

## Prerequisites

- Java 11 or higher
- Maven (for dependency management)

## Getting Started

### Clone the Repository

```sh
git clone https://github.com/your-username/snakes-and-ladders-game.git
cd snakes-and-ladders-game
```

### Build the Project

Run the following command to build the project using Maven:

```sh
mvn clean install
```

### Configure Application

1. **GameConstant**: configuration are mentioned in GameConstants file 

### Run the Application

To start the application, use:

```sh
mvn spring-boot:run
```

Alternatively, you can run the built JAR file:

```sh
java -jar target/snakes-and-ladders-game.jar
```

### Usage

Once the application is running, it will attempt to load the game configuration from the specified file. If the file is not available or an error occurs, it will use default settings based on the game version defined in `GameConstants`.

To play the game, ensure that the `playGame()` method is correctly implemented in your `SnakesAndLadders` class.

## Game Versions

- **Version 1**: Standard random game configuration.
- **Version 2**: Enhanced random game configuration with additional features.

## Contributing

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any queries or issues, please contact:

- **Your Name**: [vipul07.mathuria@gmail.com](mailto:vipul07.mathuria@gmail.com)
- **GitHub Profile**: [vipul1999](https://github.com/vipul1999)

---

Feel free to adjust any details or instructions based on your project’s specific requirements or structure.