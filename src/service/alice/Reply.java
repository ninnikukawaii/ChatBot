package service.alice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Reply extends Parent implements Workability{
    private Response response;
    private Boolean end_session = false;
    private Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();


    public void SetCommand(String command){
        this.response.SetText(command);
    }
    public String GetCommand(){
        return this.response.GetText();
    }

    public void SetEndSession(){
        this.end_session = true;
    }
    public void SetNotEndSession(){ this.end_session = false; }

    @Override
    public String ConvertToGson() {
        return gsonBuilder.toJson(this);
    }

    @Override
    public void ConvertFromGson(String gson) {
        Reply replyFromGson = gsonBuilder.fromJson(gson, Reply.class);
        this.response =  replyFromGson.response;
        this.version = replyFromGson.version;
        this.session = replyFromGson.session;
        this.end_session = replyFromGson.end_session;
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