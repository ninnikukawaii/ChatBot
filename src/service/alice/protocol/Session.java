package service.alice.protocol;

public class Session {
    private String session_id;
    private int message_id;
    private String user_id;

    public Session(String session_id, int message_id, String user_id){
        this.session_id = session_id;
        this.message_id = message_id;
        this.user_id = user_id;
    }

    String getUserId() { return user_id; }

    String getSessionId() { return session_id; }

    int getMessageId() { return message_id; }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  Session) {
            Session other = (Session) obj;
            return this.session_id.equals(other.session_id) &&
                    this.message_id == other.message_id &&
                    this.user_id.equals(other.user_id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = session_id.hashCode();
        hashCode = prime * hashCode + message_id;
        hashCode = prime * hashCode + user_id.hashCode();
        return hashCode;
    }
}
