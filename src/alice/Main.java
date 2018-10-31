package alice;

import service.exceptions.SSLContextCreationException;
import service.exceptions.WebHookException;

public class Main {

    public static void main(String[] args) throws SSLContextCreationException, WebHookException {
        WebHookServer server = new WebHookServer(8080);
        server.startServer();
    }
}
