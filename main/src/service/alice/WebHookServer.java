package src.service.alice;

import src.service.IOManager;
import src.service.alice.protocol.Query;
import src.service.alice.protocol.Reply;
import src.service.quiz.QuizParsingException;

import fi.iki.elonen.NanoHTTPD;
import src.service.textGenerator.TextParsingException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

import static src.service.Constants.*;

public class WebHookServer extends NanoHTTPD {

    private RequestHandler requestHandler;

    public WebHookServer(int serverPort) throws SSLContextCreationException, TextParsingException {
        super(serverPort);
        requestHandler = new RequestHandler(IOManager.getFilePath(TEXT_FILE));
        SSLContext sslContext = createSSLContext();
        makeSecure(sslContext.getServerSocketFactory(), null);
    }

    public void startServer() throws WebHookException {
        try {
            start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        } catch (IOException e) {
            throw new WebHookException("Ошибка при запуске сервера!", e);
        }
        System.out.println("Сервер запущен!");
    }

    @Override
    public Response serve(IHTTPSession session)
    {
        Method method = session.getMethod();
        Map<String, String> body = new HashMap<>();
        try {
            session.parseBody(body);
        } catch (IOException | ResponseException e) {
            e.printStackTrace();
        }

        if (Method.OPTIONS.equals(method)) {
            return respondToOptions();
        }

        return respondToPost(body);
    }

    private Response respondToOptions() {
        Response response = newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, "");
        addHeaders(response);
        return response;
    }

    private Response respondToPost(Map<String, String> requestBody) {
        String request = requestBody.get("postData");
        Query query = new Query(request);
        String responseGson = "";
        try {
            Reply reply = requestHandler.handleRequest(query);
            responseGson = reply.getGson();
        } catch (QuizParsingException e) {
            e.printStackTrace();
        }
        Response response = newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, responseGson);
        addHeaders(response);
        return response;
    }

    private void addHeaders(Response response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Content-Type", MIME_PLAINTEXT);
    }

    private SSLContext createSSLContext() throws SSLContextCreationException {
        try {
            char[] password = KEYSTORE_PASSWORD.toCharArray();

            KeyStore keyStore = KeyStore.getInstance(KEYSTORE_TYPE);
            FileInputStream stream = new FileInputStream(IOManager.getFilePath(KEYSTORE_FILE));
            keyStore.load(stream, password);

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KEY_MANAGER_TYPE);
            keyManagerFactory.init(keyStore, password);
            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();

            SSLContext sslContext = SSLContext.getInstance(SSL_PROTOCOL_TYPE);
            sslContext.init(keyManagers, null, null);

            return sslContext;
        }
        catch (Exception ex) {
            throw new SSLContextCreationException("Ошибка при создании SSL-соединения!", ex);
        }
    }
}
