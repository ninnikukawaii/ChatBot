package alice;

import service.FileProcessor;
import service.PreProcessor;
import service.exceptions.SSLContextCreationException;
import service.exceptions.WebHookException;

import fi.iki.elonen.NanoHTTPD;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

import static service.Constants.*;


public class WebHookServer extends NanoHTTPD {

    private PreProcessor preProcessor = new PreProcessor();

    public WebHookServer(int serverPort) throws SSLContextCreationException {
        super(serverPort);
        SSLContext sslContext = createSSLContext();
        makeSecure(sslContext.getServerSocketFactory(), null);
    }

    public void startServer() throws WebHookException {
        try {
            start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        } catch (IOException e) {
            throw new WebHookException("Ошибка при запуске сервера!", e);
        }
        System.out.println("Started");
    }

    @Override
    public Response serve(IHTTPSession session)
    {
        System.out.println("Connected");
        Map<String, String> body = new HashMap<>();
        try {
            session.parseBody(body);
        } catch (IOException | ResponseException e) {
            e.printStackTrace();
        }
        Method method = session.getMethod();

        if (Method.OPTIONS.equals(method)) {
            return respondToOptions();
        }

        return respondToPost(body);
    }

    private Response respondToOptions() {
        Response response = newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, "");
        response = addHeaders(response);
        return response;
    }

    private Response respondToPost(Map<String, String> requestBody) {
        String request = requestBody.get("postData");
        //Query req = new Query();
        //Gson requestGSON = req.ConvertToGson()
        //String responseGSON = preProcessor.HandleRequest(new Gson(request));
        String responseGSON = "";
        Response response = newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, responseGSON);
        response = addHeaders(response);
        return response;
    }

    private Response addHeaders(Response response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Content-Type", MIME_PLAINTEXT);
        return response;
    }

    private SSLContext createSSLContext() throws SSLContextCreationException {
        try {
            char[] password = KEYSTORE_PASSWORD.toCharArray();

            KeyStore keyStore = KeyStore.getInstance("JKS");
            FileInputStream stream = new FileInputStream(FileProcessor.getFilePath(KEYSTORE_PATH));
            keyStore.load(stream, password);

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, password);
            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();

            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(keyManagers, null, null);

            return sslContext;
        }
        catch (Exception ex) {
            throw new SSLContextCreationException("Ошибка при создании SSL-соединения!", ex);
        }
    }
}
