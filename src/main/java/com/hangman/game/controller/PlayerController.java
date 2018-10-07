package com.hangman.game.controller;

import com.hangman.game.data.entity.Player;
import com.hangman.game.response.JsonResponse;
import com.hangman.game.service.PlayerService;
import com.hangman.game.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Burak Naroglu
 */

@RestController
@RequestMapping("${spring.data.rest.basePath}/player")
public class PlayerController extends BaseEntityController<Player, PlayerService> {

    @Autowired
    private PlayerService playerService;

    @Override
    protected PlayerService getService() {
        return playerService;
    }

    @PostMapping
    public ResponseEntity<JsonResponse> savePlayer(@RequestBody Player player) {
        ServiceResult<JsonResponse> serviceResult = getService().savePlayer(player);
        return createResponse(serviceResult);
    }

}

    