package com.hangman.game.data.repository;

import com.hangman.game.data.entity.Game;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * @author Burak Naroglu
 */

@Repository
public interface GameRepository extends BaseRepository<Game> {

    Optional<List<Game>> findAllBy();

    Optional<Game> findById(Long id);

}

    