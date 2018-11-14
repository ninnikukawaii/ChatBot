package service.alice.protocol;

public class Message {
    protected Session session;
    protected String version;

    public Session getSession(){ return this.session; }

    public String getVersion(){
        return this.version;
    }

    public String getUserID() { return session.getUserId(); }

    public String getSessionID() { return session.getSessionId(); }

    public int getMessageID() { return session.getMessageId(); }
}
