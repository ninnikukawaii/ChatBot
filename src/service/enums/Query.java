package service.enums;

import java.util.HashMap;

public class Query {
    private HashMap<String, String> request = new HashMap<String, String>();
    {
        request.put("command", "");
        request.put("type", "");
    }

    private HashMap<String, Object> session = new HashMap<String, Object>();
    {
        session.put("new", false);
        session.put("message_id", 0);
        session.put("session_id", "");
        session.put("user_id", "");
    }
    private String version = "1.0";

    public Query(HashMap<String, String> request, HashMap<String, Object> session, String version){
        this.request = request;
        this.session = session;
        this.version = version;
    }

    public String GetQuery(){
        return this.request.get("command");
    }

    public HashMap<String, Object> GetSession(){
        return this.session;
    }

    public String GetVersion(){
        return this.version;
    }
}
