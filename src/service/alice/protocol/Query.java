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

    public Query(String command, String session_id, int message_id, String user_id, String version) {
        this.request = new Request(command);
        this.session = new Session(session_id, message_id, user_id);
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Query) {
            Query other = (Query)obj;
            return this.version.equals(other.version) &&
                    this.session.equals(other.session) &&
                    this.request.equals(other.request);
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = version.hashCode();
        hashCode = prime * hashCode + session.hashCode();
        hashCode = prime * hashCode + request.hashCode();
        return hashCode;
    }

    public String getCommand(){ return this.request.getCommand(); }
}
