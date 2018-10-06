package data;

import com.hangman.game.data.entity.Game;
import com.hangman.game.data.entity.Guess;
import com.hangman.game.data.entity.Player;
import com.hangman.game.enums.GameStatus;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Burak Naroglu
 */

public class TestData {

    public static List<Game> getGameList() {
        List<Game> gameList = new ArrayList<>();

        Game firstGame = new Game();
        firstGame.setId(1L);
        firstGame.setGameStatus(GameStatus.CONTINUE.toString());
        firstGame.setGuesses(3);
        firstGame.setPlayer("Test Kullanici");
        firstGame.setGuessedWord("t");
        firstGame.setGuessesLeft(3);
        firstGame.setIncorrectLetters("e");
        gameList.add(firstGame);

        Game secondGame = new Game();
        secondGame.setId(2L);
        secondGame.setGameStatus(GameStatus.LOSE.toString());
        secondGame.setGuesses(4);
        secondGame.setPlayer("Test Kullanicilar");
        secondGame.setGuessedWord("te");
        secondGame.setGuessesLeft(5);
        secondGame.setIncorrectLetters("ef");
        gameList.add(secondGame);

        return gameList;
    }

    public static List<Player> getPlayerList() {
        List<Player> playerList = new ArrayList<>();

        Player firstPlayer = new Player();
        firstPlayer.setId(1L);
        firstPlayer.setAge(18);
        firstPlayer.setName("Test Kullanici");
        playerList.add(firstPlayer);

        Player secondPlayer = new Player();
        secondPlayer.setId(1L);
        secondPlayer.setAge(18);
        secondPlayer.setName("Test Kullanici");
        playerList.add(secondPlayer);

        return playerList;
    }

    public static Guess getGuess() {
        Guess guess = new Guess();
        guess.setLetter("t");
        return guess;
    }

}

    