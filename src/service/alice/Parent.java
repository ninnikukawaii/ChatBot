package service.alice;

public class Parent {
    protected Session session;
    protected String version;

    public void SetSession(Session session){
        this.session = session;
    }
    public Session GetSession(){
        return this.session;
    }

    public void SetVersion(String version){
        this.version = version;
    }
    public String GetVersion(){
        return this.version;
    }


}


class Session {
    private String session_id;
    private Integer message_id;
    private String user_id;

    public Session(String session_id, Integer  message_id, String user_id){
        this.session_id = session_id;
        this.message_id = message_id;
        this.user_id = user_id;
    }
}
