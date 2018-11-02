package service.alice;

public class Request {
    private String command;

    public String GetCommand(){
        return this.command;
    }

    public void SetCommand(String command){
        this.command = command;
    }

    public Request(String command){
        this.command = command;
    }
}