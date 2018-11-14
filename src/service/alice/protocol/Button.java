package service.alice.protocol;

import service.userAnswerProcessing.UserStateType;

import java.util.HashMap;

public class Button {
    private String title;
    //private String payload;
    private Boolean hide;

    public static final Button showHelp = new Button("Справка");
    public static final Button startQuiz = new Button("Викторина");
    public static final Button exitQuiz = new Button("Выход");
    public static final Button showScore = new Button("Счет");
    public static final Button exit = new Button("Выход");

    public static final HashMap<UserStateType, Button[]> defaultButtons;
    static {
        defaultButtons = new HashMap<>();
        defaultButtons.put(UserStateType.Chat,
                new Button[]{Button.showHelp, Button.startQuiz, Button.exit});
        defaultButtons.put(UserStateType.Quiz,
                new Button[]{Button.showHelp, Button.showScore, Button.exitQuiz});
    }

    public String getTitle() { return title; }

    public String getCommand() {
        return this.title;
    }

    private Button(String title){
        this.title = title;
        this.hide = true;
    }
}
