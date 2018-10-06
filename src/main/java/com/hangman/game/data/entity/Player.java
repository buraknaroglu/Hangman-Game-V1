package com.hangman.game.data.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * @author Burak Naroglu
 */

@Data
@Entity(name = "PLAYERS")
public class Player {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

}

    