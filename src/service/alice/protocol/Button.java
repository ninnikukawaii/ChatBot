package service.alice.protocol;

import service.userAnswerProcessing.Command;

public class Button {
    private String title;
    private Request payload;
    private Boolean hide;

    public static Button showHelp = new Button("Показать справку", Command.Help.getName());
    public static Button startQuiz = new Button("Запустить викторину", Command.Quiz.getName());
    public static Button exitQuiz = new Button("Завершить викторину", Command.Exit.getName());
    public static Button showScore = new Button("Узнать счет", Command.Score.getName());
    public static Button exit = new Button("Завершить общение", Command.Exit.getName());

    private Button(String title, String command){
        this.title = title;
        this.payload = new Request(command);
    }
}
