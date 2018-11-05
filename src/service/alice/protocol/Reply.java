package service.alice.protocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Reply extends Parent {
    private Response response = new Response();

    public void setEndSession() {
        this.response.setEndSession();
    }

    public Reply(String text, Boolean end_session, Session session, String version) {
        this.response.setText(text);
        if (end_session){
            this.response.setEndSession();
        }
        this.session = session;
        this.version = version;
    }

    public String convertToGson() {
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        return gsonBuilder.toJson(this);
    }

    public void setButtons(Button[] buttons) {
        this.response.setButtons(buttons);
    }
}
