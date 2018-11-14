package service.userAnswerProcessing;

import java.util.ArrayList;

public class StandardResponse {
    public static final ArrayList<String> CHAT_GREETING = new ArrayList<String>() {{
        add("Привет!");
        add("Рад видеть тебя, человек!");
        add("Если хочешь получить справку или поиграть в викторину, просто попроси меня.");
    }};

    public static final ArrayList<String> QUIZ_GREETING = new ArrayList<String>() {{
        add("Добро пожаловать в викторину!");
        add("У меня для тебя есть большая база из интересных вопросов.");
        add("Удачной игры!");
    }};

    public static final ArrayList<String> CHAT_HELP = new ArrayList<String>() {{
        add("Привет, я чатбот!");
        add("Я создан примитивным человеческим мозгом, дабы развлечь тебя и отвлечь " +
                "от непрерывного марша дней, приближающих конец вселенной.");
        add("");
        add("Ты можешь:");
        add("\t1) Поиграть со мной в викторину");
        add("\t2) Вызвать справку");
        add("\t3) Попрощаться со мной, для этого просто напиши \"выход\"");
        add("");
        add("Обращайся!");
    }};

    public static final ArrayList<String> QUIZ_HELP = new ArrayList<String>() {{
        add("Чтобы узнать свой счет, введи \"счет\".");
        add("Если тебе надоест, ты можешь завершить игру, для этого просто введи \"выход\".");
    }};

    public static final String CORRECT_ANSWER = "Правильно!";
    public static final String INCORRECT_ANSWER = "Неправильно, правильный ответ: " ;
    public static final String QUIZ_FAREWELL = "Приятно было поиграть с тобой!";
    public static final String CHAT_FAREWELL = "Приятно было пообщаться с тобой!";
    public static final String NO_MORE_QUESTIONS = "Кажется, у меня закончились вопросы...";
    public static final String MISUNDERSTOOD = "Кажется, я не понял тебя. Может попросишь справку?";
}
