package com.hangman.game.service;

import com.hangman.game.constants.GlobalConstants;
import com.hangman.game.data.entity.Game;
import com.hangman.game.data.entity.Guess;
import com.hangman.game.data.entity.Player;
import com.hangman.game.data.repository.GameRepository;
import com.hangman.game.response.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @author Burak Naroglu
 */

@Service
public class GameService extends BaseService<Game> {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public GameRepository getRepository() {
        return gameRepository;
    }

    @Override
    protected ServiceResult<JsonResponse<List<Game>>> getAllResponse(List<Game> entities) {
        if (entities.size() > 0) {
            return new ServiceResult<>(new JsonResponse(GlobalConstants.GAME_LIST_SUCCESS_MESSAGE, entities), HttpStatus.OK);
        } else {
            return new ServiceResult<>(new JsonResponse(GlobalConstants.GLOBAL_ERROR_MESSAGE));
        }
    }

    @Override
    protected ServiceResult<JsonResponse<Game>> getEntityResponse(Optional<Game> optionalEntity) {
        if (optionalEntity.isPresent()) {
            return new ServiceResult<>(new JsonResponse(GlobalConstants.GAME_SUCCESS_MESSAGE, optionalEntity.get()), HttpStatus.OK);
        } else {
            return new ServiceResult<>(new JsonResponse(GlobalConstants.GAME_NOT_FOUND_MESSAGE), HttpStatus.NOT_FOUND);
        }
    }

    public ServiceResult<JsonResponse> startGame(Player player) {
        try {
            Game game = new Game();
            game.setPlayer(player.getName());
            game.setGuesses(0);
            getRepository().save(game);
        } catch (Exception e) {
            log.error("startGame got exception:", e.getMessage(), e);
            return new ServiceResult<>(new JsonResponse(GlobalConstants.GLOBAL_ERROR_MESSAGE), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ServiceResult<>(new JsonResponse(GlobalConstants.START_GAME_SUSSCESS_MESSAGE), HttpStatus.OK);
    }

    public ServiceResult<JsonResponse> guess(Long gameId, Guess guess) {
        Optional<Game> optionalGame = getRepository().findById(gameId);
        if (optionalGame.isPresent()) {
            Integer guessCount = optionalGame.get().getGuesses();
            optionalGame.get().setGuesses(++guessCount);
            getRepository().save(optionalGame.get());
            return new ServiceResult<>(new JsonResponse(GlobalConstants.GUESS_SUCCESS_MESSAGE), HttpStatus.OK);
        }
        return new ServiceResult<>(new JsonResponse(GlobalConstants.GUESS_ERROR_MESSAGE), HttpStatus.NOT_FOUND);
    }

    public ServiceResult<JsonResponse> finishGame(Long gameId) {
        Optional<Game> optionalGame = getRepository().findById(gameId);
        if (optionalGame.isPresent()) {
            return new ServiceResult<>(new JsonResponse(GlobalConstants.GAME_FINISHE_SUCCESS_MESSAGE), HttpStatus.OK);
        }
        return new ServiceResult<>(new JsonResponse(GlobalConstants.GAME_NOT_FOUND_MESSAGE), HttpStatus.NOT_FOUND);
    }

}

    