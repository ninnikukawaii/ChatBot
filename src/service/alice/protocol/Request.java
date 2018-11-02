package service.alice.protocol;

public class Request {
    private String command;

    String GetCommand(){
        return this.command;
    }

    void SetCommand(String command){
        this.command = command;
    }

    public Request(String command){
        this.command = command;
    }
}