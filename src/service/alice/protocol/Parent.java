package service.alice.protocol;

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

    public String GetUserID() {return session.GetUserId();}

}


