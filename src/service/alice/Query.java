package service.alice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Query extends Parent{
    private Request request;
    private Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();

    public void SetCommand(String command){ this.request.SetCommand(command); }
    public String GetCommand(){ return this.request.GetCommand(); }

    public void ConvertFromGson(String gson) {
        Query queryFromGson = gsonBuilder.fromJson(gson, Query.class);
        this.request =  queryFromGson.request;
        this.version = queryFromGson.version;
        this.session = queryFromGson.session;
    }

    public String GetUserID(){return super.GetUserID();}
}

class Request {
    private String command;

    public void SetCommand(String command){
        this.command = command;
    }

    public String GetCommand(){
        return this.command;
    }

    public Request(String command){
        this.command = command;
    }
}


