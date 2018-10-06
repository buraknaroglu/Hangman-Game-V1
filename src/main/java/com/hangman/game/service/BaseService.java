package com.hangman.game.service;

import com.hangman.game.constants.GlobalConstants;
import com.hangman.game.data.repository.BaseRepository;
import com.hangman.game.response.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

/**
 * @author Burak Naroglu
 */

public abstract class BaseService<Entity> extends BaseServiceProxy<Entity, BaseRepository<Entity>> {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public ServiceResult<JsonResponse<List<Entity>>> getAll() {
        List<Entity> entities = getRepository().findAll();
        return getAllResponse(entities);
    }

    public ServiceResult<JsonResponse<Entity>> get(Long id) {
        Optional<Entity> optional = getRepository().findById(id);
        return getEntityResponse(optional);
    }

    protected ServiceResult<JsonResponse<List<Entity>>> getAllResponse(List<Entity> entities) {
        if (entities != null) {
            return new ServiceResult<>(new JsonResponse(GlobalConstants.GLOBAL_SUCCESS_MESSAGE, entities), HttpStatus.OK);
        } else {
            return new ServiceResult<>(new JsonResponse(GlobalConstants.GLOBAL_ERROR_MESSAGE));
        }
    }

    protected ServiceResult<JsonResponse<Entity>> getEntityResponse(Optional<Entity> optionalEntity) {
        if (optionalEntity.isPresent()) {
            return new ServiceResult<>(new JsonResponse(GlobalConstants.GLOBAL_SUCCESS_MESSAGE, optionalEntity.get()), HttpStatus.OK);
        } else {
            return new ServiceResult<>(new JsonResponse(GlobalConstants.GLOBAL_ERROR_MESSAGE));
        }
    }

}

    