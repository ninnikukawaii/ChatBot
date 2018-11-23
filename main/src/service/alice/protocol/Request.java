package src.service.alice.protocol;

public class Request {
    private String command;

    Request(String command){
        this.command = command;
    }

    String getCommand(){
        return this.command;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  Request) {
            return this.command.equals(((Request) obj).command);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return command.hashCode();
    }
}
