package service.enums;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WorkWithJson {
    private Query query;
    private Response response;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String GetQuery(String json){
        this.query = gson.fromJson(json, Query.class);

        return this.query.GetQuery();
    }

    public String GetResponse(String line){
        response = new Response(line);
        InformationExchange();
        return gson.toJson(response);
    }

    private void InformationExchange(){
        response.SetInformation(query.GetSession());
        response.SetVersion(query.GetVersion());

    }
}
