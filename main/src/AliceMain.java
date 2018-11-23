package src;

import src.service.alice.WebHookServer;
import src.service.alice.SSLContextCreationException;
import src.service.alice.WebHookException;
import src.service.textGenerator.TextParsingException;

import static src.service.Constants.SERVER_PORT;

public class AliceMain {

    public static void main(String[] args) throws SSLContextCreationException,
            WebHookException, TextParsingException {
        WebHookServer server = new WebHookServer(SERVER_PORT);
        server.startServer();
    }
}
