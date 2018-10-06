package com.hangman.game.enums;

/**
 * @author Burak Naroglu
 */
public enum GameStatus {

    CONTINUE(1, "Devam ediyor"),
    WIN(2, "Kazanildi"),
    LOSE(3, "Kaybedildi"),;

    GameStatus(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private Integer value;

    private String desc;

}
