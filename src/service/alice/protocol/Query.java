package service.alice.protocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Query extends Parent{
    private Request request;

    public String GetCommand(){ return this.request.getCommand(); }

    public Query(String gson) {
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        Query queryFromGson = gsonBuilder.fromJson(gson, Query.class);
        System.out.println(queryFromGson.request.payload.getCommand());
        this.request =  queryFromGson.request;
        this.version = queryFromGson.version;
        this.session = queryFromGson.session;
    }

    public Boolean havePayload(){ return this.request.havePayload(); }

    public String getPayload(){
        return this.request.getPaylod();
    }

    public String getUserID(){return super.GetUserID();}
}


