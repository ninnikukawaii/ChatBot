package service.alice.protocol;

public class Request {
    private String command;

    public Request(String command){
        this.command = command;
    }

    String getCommand(){
        return this.command;
    }
}
