package service.alice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Reply extends Parent{
    private Response response = new Response();
    private Boolean end_session = false;

    public void SetCommand(String command){
        this.response.SetText(command);
    }
    public String GetCommand(){
        return this.response.GetText();
    }

    public void SetEndSession(){
        this.end_session = true;
    }

    public Reply(String text, Boolean end_session, Session session, String version){
        this.response.SetText(text);
        this.end_session = end_session;
        this.session = session;
        this.version = version;
    }

    public String ConvertToGson() {
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        return gsonBuilder.toJson(this);
    }
}

class Response {
    private String text;
    public void SetText(String text){
        this.text = text;
    }
    public String GetText(){
        return this.text;
    }
}