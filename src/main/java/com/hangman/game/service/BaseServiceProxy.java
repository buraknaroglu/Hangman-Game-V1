package com.hangman.game.service;

import com.hangman.game.data.repository.BaseRepository;

/**
 * @author Burak Naroglu
 */

public abstract class BaseServiceProxy<Entity, Repository extends BaseRepository<Entity>> {

    public abstract Repository getRepository();

}
