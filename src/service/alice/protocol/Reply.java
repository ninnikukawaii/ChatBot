package service.alice.protocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Reply extends Message {
    private Response response;

    public Reply(String text, Boolean end_session, Session session, String version) {
        this.response = new Response(text, end_session);
        this.session = session;
        this.version = version;
    }

    public void setEndSession() {
        this.response.setEndSession();
    }

    public String getGson() {
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        return gsonBuilder.toJson(this);
    }

    public void setButtons(Button[] buttons) {
        this.response.setButtons(buttons);
    }
}
