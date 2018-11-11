package service.alice.protocol;

public class Request {
    private String command;
    public Request payload;

    String getCommand(){
        return this.command;
    }

    public Request(String command){
        this.command = command;
    }

    Boolean havePayload(){ return !(this.getPaylod() == null); }

    String getPaylod() { return this.payload.getCommand(); }
}