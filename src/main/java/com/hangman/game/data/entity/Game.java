package com.hangman.game.data.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * @author Burak Naroglu
 */

@Data
@Entity(name = "GAMES")
public class Game {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @Column(name = "player")
    private String player;

    @Column(name = "guesses")
    private Integer guesses;

    @Column(name = "guesses_left")
    private Integer guessesLeft;

    @Column(name = "guessed_word")
    private String guessedWord;

    @Column(name = "incorrect_letters")
    private String incorrectLetters;

    @Column(name = "game_status")
    private String gameStatus;

}

    