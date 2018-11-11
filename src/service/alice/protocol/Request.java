package service.alice.protocol;

public class Request {
    private String command;
    private Request payload;

    public Request(String command){
        this.command = command;
    }

    String getCommand(){
        return this.command;
    }

    Boolean hasPayload(){ return !(this.getPayload() == null); }

    String getPayload() { return this.payload.getCommand(); }
}
