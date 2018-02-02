package com.erikalves.application.utils;

public class RestApiResponseTo<T> {

    private T results;

    public RestApiResponseTo(T results) {
        this.results = results;
    }

    public T getResults() {
        return this.results;
    }

}
