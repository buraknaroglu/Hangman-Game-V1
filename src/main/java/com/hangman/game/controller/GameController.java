package com.hangman.game.controller;

import com.hangman.game.data.entity.Game;
import com.hangman.game.data.entity.Guess;
import com.hangman.game.data.entity.Player;
import com.hangman.game.response.JsonResponse;
import com.hangman.game.service.GameService;
import com.hangman.game.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Burak Naroglu
 */

@RestController
@RequestMapping("/game")
public class GameController extends BaseEntityController<Game, GameService> {

    @Autowired
    private GameService gameService;

    @Override
    protected GameService getService() {
        return gameService;
    }

    @PostMapping
    public ResponseEntity<JsonResponse> startGame(@RequestBody Player player) {
        ServiceResult<JsonResponse> serviceResult = getService().startGame(player);
        return createResponse(serviceResult);
    }

    @PutMapping(value = "/{gameId}")
    public ResponseEntity<JsonResponse> guess(@PathVariable("gameId") Long gameId, @RequestBody Guess guess) {
        ServiceResult<JsonResponse> serviceResult = getService().guess(gameId, guess);
        return createResponse(serviceResult);
    }

    @DeleteMapping(value = "/{gameId}")
    public ResponseEntity<JsonResponse> finishGame(@PathVariable("gameId") Long gameId) {
        ServiceResult<JsonResponse> serviceResult = getService().finishGame(gameId);
        return createResponse(serviceResult);
    }

}

    