package com.erikalves.application.utils;

public class Response<T> {

    private T results;

    public Response(T results) {
        this.results = results;
    }

    public T getResults() {
        return this.results;
    }

}
