package com.hangman.game.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Burak Naroglu
 */

@Data
@AllArgsConstructor
public class JsonResponse<T> {

    private String description;

    private T result;

    public JsonResponse(String description) {
        this.description = description;
    }

    public JsonResponse(T result) {
        this.result = result;
    }

}

    