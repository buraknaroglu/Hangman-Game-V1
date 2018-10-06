package com.hangman.game.data.repository;

import com.hangman.game.data.entity.Player;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * @author Burak Naroglu
 */

@Repository
public interface PlayerRepository extends BaseRepository<Player> {

    Optional<Player> findByName(String name);

}

    