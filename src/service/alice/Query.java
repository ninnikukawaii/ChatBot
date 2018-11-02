package service.alice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Query extends Parent{
    private Request request;
    private Request payload;

    public String GetCommand(){ return this.request.GetCommand(); }

    public void Query(String gson) {
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        Query queryFromGson = gsonBuilder.fromJson(gson, Query.class);
        this.payload = queryFromGson.payload;
        this.request =  queryFromGson.request;
        this.version = queryFromGson.version;
        this.session = queryFromGson.session;
    }

    public Boolean havePayload(){
        if (this.payload == null){
            return false;
        }
        return true;
    }

    public String getPayload(){
        return this.payload.GetCommand();
    }

    public String GetUserID(){return super.GetUserID();}
}


