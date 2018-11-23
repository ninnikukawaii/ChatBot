package src.service.userAnswerProcessing;

import java.util.Arrays;
import java.util.List;

public class StandardResponse {
    public static final List<String> CHAT_GREETING = Arrays.asList(
            "Привет!",
            "Рад видеть тебя, человек!",
            "Если хочешь получить справку или поиграть в викторину, просто попроси меня.");

    public static final List<String> QUIZ_GREETING = Arrays.asList(
            "Добро пожаловать в викторину!",
            "У меня для тебя есть большая база из интересных вопросов.",
            "Удачной игры!");

    public static final List<String> CHAT_HELP = Arrays.asList(
            "Привет, я чатбот!",
            "Я создан примитивным человеческим мозгом, дабы развлечь тебя и отвлечь " +
                "от непрерывного марша дней, приближающих конец вселенной.",
            "",
            "Ты можешь:",
            "\t1) Поиграть со мной в викторину, для этого напиши \"викторина\"",
            "\t3) Попросить меня рассказать историю, для этого напиши \"текст\"",
            "\t3) Вызвать справку, для этого напиши \"справка\"",
            "\t4) Попрощаться со мной, для этого напиши \"выход\"",
            "",
            "Обращайся!");

    public static final List<String> QUIZ_HELP = Arrays.asList(
            "Чтобы узнать свой счет, введи \"счет\".",
            "Если тебе надоест, ты можешь завершить игру, для этого введи \"выход\".");

    public static final String CORRECT_ANSWER = "Правильно!";
    public static final String INCORRECT_ANSWER = "Неправильно, правильный ответ: " ;
    public static final String QUIZ_FAREWELL = "Приятно было поиграть с тобой!";
    public static final String CHAT_FAREWELL = "Приятно было пообщаться с тобой!";
    public static final String NO_MORE_QUESTIONS = "Кажется, у меня закончились вопросы...";
    public static final String MISUNDERSTOOD = "Кажется, я не понял тебя. Может попросишь справку?";
}
