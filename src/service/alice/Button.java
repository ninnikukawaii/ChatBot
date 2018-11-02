package service.alice;

public class Button {
    private String title;
    private Request payload;
    private Boolean hide;

    public void Button(String title, String command){
        this.title = title;
        this.payload.SetCommand(command);
    }
    public void ShowOnce(){
        this.hide = true;
    }
}

