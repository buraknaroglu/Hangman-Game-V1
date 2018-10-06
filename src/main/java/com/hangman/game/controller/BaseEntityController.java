package com.hangman.game.controller;

import com.hangman.game.response.JsonResponse;
import com.hangman.game.service.BaseService;
import com.hangman.game.service.ServiceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Burak Naroglu
 */

@Component
public abstract class BaseEntityController<Entity, Service extends BaseService<Entity>> {

    protected abstract Service getService();

    @GetMapping
    public ResponseEntity getAll() throws URISyntaxException {
        ServiceResult<JsonResponse<List<Entity>>> serviceResult = getService().getAll();
        return createResponse(serviceResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@Valid @PathVariable Long id) {
        ServiceResult<JsonResponse<Entity>> serviceResult = getService().get(id);
        return createResponse(serviceResult);
    }

    protected ResponseEntity createResponse(ServiceResult serviceResult) {
        return new ResponseEntity<>(serviceResult.getResult(), serviceResult.getHttpStatus());
    }

}

    