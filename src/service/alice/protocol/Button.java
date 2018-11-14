package service.alice.protocol;

import service.userAnswerProcessing.Command;
import service.userAnswerProcessing.UserStateType;

import java.util.HashMap;

public class Button {
    private String title;
    private Boolean hide;

    public static final Button HELP = new Button(Command.HELP.getName());
    public static final Button QUIZ = new Button(Command.QUIZ.getName());
    public static final Button SCORE = new Button(Command.SCORE.getName());
    public static final Button EXIT = new Button(Command.EXIT.getName());

    private Button(String title){
        this.title = title;
        this.hide = true;
    }

    public String getTitle() { return title; }

    public static Button[] getDefaultButtons(UserStateType userState) {
        if (defaultButtons.containsKey(userState)) {
            return defaultButtons.get(userState);
        }

        return null;
    }

    private static final HashMap<UserStateType, Button[]> defaultButtons;
    static {
        defaultButtons = new HashMap<>();
        defaultButtons.put(UserStateType.CHAT,
                new Button[]{Button.HELP, Button.QUIZ, Button.EXIT});
        defaultButtons.put(UserStateType.QUIZ,
                new Button[]{Button.HELP, Button.SCORE, Button.EXIT});
    }
}
