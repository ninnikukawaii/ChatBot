package main;

import service.alice.WebHookServer;
import service.alice.SSLContextCreationException;
import service.alice.WebHookException;

public class AliceMain {

    public static void main(String[] args) throws SSLContextCreationException, WebHookException {
        WebHookServer server = new WebHookServer(8080);
        server.startServer();
    }
}
