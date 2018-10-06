package com.hangman.game.service;

import org.springframework.http.HttpStatus;

/**
 * @author Burak Naroglu
 */

public class ServiceResult<T> {

    private T result;

    private HttpStatus httpStatus = HttpStatus.OK;

    public ServiceResult(T result) {
        this.result = result;
    }

    public ServiceResult(T result, HttpStatus httpStatus) {
        this.result = result;
        this.httpStatus = httpStatus;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
