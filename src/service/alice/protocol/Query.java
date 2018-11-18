package service.alice.protocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Query extends Message {
    private Request request;

    public Query(String gson) {
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        Query queryFromGson = gsonBuilder.fromJson(gson, Query.class);
        this.request =  queryFromGson.request;
        this.version = queryFromGson.version;
        this.session = queryFromGson.session;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()){
            return false;
        }
        Query query = (Query)obj;
        if (!this.version.equals(query.version)){
            return false;
        }
        if (this.session.getMessageId().equals(query.session.getMessageId())){
            return false;
        }
        if (this.session.getSessionId().equals(query.session.getSessionId())){
            return false;
        }
        if (this.session.getUserId().equals(query.session.getUserId())){
            return false;
        }
        if (this.request.getCommand().equals(query.request.getCommand())){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getCommand(){ return this.request.getCommand(); }
}
