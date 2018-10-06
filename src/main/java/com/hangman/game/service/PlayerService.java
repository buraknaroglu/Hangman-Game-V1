package com.hangman.game.service;

import com.hangman.game.constants.GlobalConstants;
import com.hangman.game.data.entity.Player;
import com.hangman.game.data.repository.PlayerRepository;
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
public class PlayerService extends BaseService<Player> {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public PlayerRepository getRepository() {
        return playerRepository;
    }

    @Override
    protected ServiceResult<JsonResponse<List<Player>>> getAllResponse(List<Player> entities) {
        if (entities.size() > 0) {
            return new ServiceResult<>(new JsonResponse(GlobalConstants.PLAYER_LIST_SUCCESS_MESSAGE, entities), HttpStatus.OK);
        } else {
            return new ServiceResult<>(new JsonResponse(GlobalConstants.GLOBAL_ERROR_MESSAGE));
        }
    }

    public ServiceResult<JsonResponse> savePlayer(Player player) {
        Optional<Player> optionalPlayer = getRepository().findByName(player.getName());
        if (optionalPlayer.isPresent()) {
            return new ServiceResult<>(new JsonResponse(GlobalConstants.SAVE_PLAYER_ERROR_MESSAGE), HttpStatus.BAD_REQUEST);
        } else {
            getRepository().save(player);
            return new ServiceResult<>(new JsonResponse(GlobalConstants.SAVE_PLAYER_SUCCESS_MESSAGE), HttpStatus.OK);
        }
    }

    @Override
    protected ServiceResult<JsonResponse<Player>> getEntityResponse(Optional<Player> optionalEntity) {
        if (optionalEntity.isPresent()) {
            return new ServiceResult<>(new JsonResponse(GlobalConstants.GET_PLAYER_SUCCESS_MESSAGE, optionalEntity.get()), HttpStatus.OK);
        } else {
            return new ServiceResult<>(new JsonResponse(GlobalConstants.GET_PLAYER_ERROR_MESSAGE), HttpStatus.NOT_FOUND);
        }
    }
}

    