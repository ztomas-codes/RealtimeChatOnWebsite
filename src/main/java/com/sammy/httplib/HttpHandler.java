package com.sammy.httplib;

public interface HttpHandler {
    public void handle(Request request, Response response) throws Exception;
}
