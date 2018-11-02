package service.alice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Reply extends Parent{
    private Response response = new Response();
    private Boolean end_session = false;
    private Button[] buttons;

    public void SetCommand(String command){
        this.response.SetText(command);
    }
    public String GetCommand(){
        return this.response.GetText();
    }

    public void SetEndSession(){
        this.end_session = true;
    }

    public Reply(String text, Boolean end_session, Session session, String version){
        this.response.SetText(text);
        this.end_session = end_session;
        this.session = session;
        this.version = version;
    }

    public String ConvertToGson() {
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        return gsonBuilder.toJson(this);
    }

    public void addButtonOnChat(){
        Button buttonOnChat = new Button();
        Button buttonOnReference = new Button();
        Button buttonOnExit = new Button();
        buttonOnChat.Button("Запустить викторину", "Викторина");
        buttonOnReference.Button("Показать справку", "Справка");
        buttonOnExit.Button("Завершить общение", "Выход");
        this.buttons = new Button[3];
        this.buttons[0] = buttonOnChat;
        this.buttons[1] = buttonOnReference;
        this.buttons[2] = buttonOnExit;
    }

    public void addButtonOnQuiz(){
        Button buttonOnReference = new Button();
        Button buttonOnScore = new Button();
        Button buttonOnExit = new Button();
        buttonOnReference.Button("Показать справку", "Справка");
        buttonOnScore.Button("Узнать счет", "Счет");
        buttonOnExit.Button("Завершить викторину", "Выход");
        this.buttons = new Button[3];
        this.buttons[0] = buttonOnScore;
        this.buttons[1] = buttonOnReference;
        this.buttons[2] = buttonOnExit;
    }
}

class Response {
    private String text;
    public void SetText(String text){
        this.text = text;
    }
    public String GetText(){
        return this.text;
    }
}