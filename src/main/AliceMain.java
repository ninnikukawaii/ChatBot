package main;

import service.alice.WebHookServer;
import service.alice.SSLContextCreationException;
import service.alice.WebHookException;

import static service.Constants.SERVER_PORT;

public class AliceMain {

    public static void main(String[] args) throws SSLContextCreationException, WebHookException {
        WebHookServer server = new WebHookServer(SERVER_PORT);
        server.startServer();
    }
}
