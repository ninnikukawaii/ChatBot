package service.alice.protocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import service.userAnswerProcessing.Command;
import service.userAnswerProcessing.UserStateType;

import java.util.HashMap;

public class Button {
    private String title;
    private String payload;
    private Boolean hide;

    public static final Button showHelp = new Button("Показать справку", Command.Help.getName());
    public static final Button startQuiz = new Button("Запустить викторину", Command.Quiz.getName());
    public static final Button exitQuiz = new Button("Завершить викторину", Command.Exit.getName());
    public static final Button showScore = new Button("Узнать счет", Command.Score.getName());
    public static final Button exit = new Button("Завершить общение", Command.Exit.getName());

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
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        Request reques = gsonBuilder.fromJson(this.payload, Request.class);
        return reques.getCommand();
    }

    private Button(String title, String command){
        this.title = title;
        Gson gson = new Gson();
        Request payloa = new Request(command);
        JsonObject jsonObject =  new JsonObject();
        jsonObject.addProperty("command", command);
        this.payload = jsonObject.toString();
    }
}
