package service.enums;

import java.util.HashMap;

public class Response {
    private HashMap<String, Object> response = new HashMap<String, Object>();
    {
        response.put("text", "");
        response.put("tts", "");
        response.put("end_session", false);
    }
    private HashMap<String, Object> session;
    private String version;

    public Response(String response){
        this.response.remove("text");
        this.response.put("text", response);
        this.response.remove("tts");
        this.response.put("tts", response);
    }

    public void SetInformation(HashMap<String, Object> session){
        this.session.put("session_id", session.get("session_id"));
        this.session.put("message_id", session.get("message_id"));
        this.session.put("user_id", session.get("user_id"));
    }

    public void SetVersion(String version){
        this.version = version;
    }
}
