package service.alice.protocol;

class Session {
    private String session_id;
    private Integer message_id;
    private String user_id;

    public Session(String session_id, Integer  message_id, String user_id){
        this.session_id = session_id;
        this.message_id = message_id;
        this.user_id = user_id;
    }

    String GetUserId() {return user_id;}
}
