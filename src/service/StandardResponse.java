package service;

public class StandardResponse {
    public static final String[] CHAT_GREETING = {"Привет!",
            "Рад видеть тебя, человек!",
            "Если хочешь получить справку или поиграть в викторину, просто попроси меня"};

    public static final String[] QUIZ_GREETING = {"Добро пожаловать в викторину!",
            "У меня для тебя есть большая база из интересных вопросов.",
            "Удачной игры!"};

    public static final String[] CHAT_HELP = {"Привет, я твой ChatBot!",
            "Я создан примитивным человеческим мозгом, дабы развлечь тебя и отвлечь",
            "от непрерывного марша дней, приближающих конец вселенной",
            "",
            "Ты можешь:",
            "\t1) Поиграть со мной в викторину",
            "\t2) Вызвать справку",
            "\t3) Попрощаться со мной, для этого просто напиши \"выход\"",
            "",
            "Обращайся!"};
    public static final String[] QUIZ_HELP = {
            "Чтобы узнать свой счет, введи \"счет\".",
            "Если тебе надоест, ты можешь завершить игру, для этого просто введи \"выход\"."};

    public static final String CORRECT_ANSWER = "Правильно!";
    public static final String INCORRECT_ANSWER = "Неправильно, правильный ответ: " ;
    public static final String QUIZ_FAREWELL = "Приятно было поиграть с тобой!";
    public static final String CHAT_FAREWELL = "Приятно было пообщаться с тобой!";
    public static final String NO_MORE_QUESTIONS = "Кажется, у меня закончились вопросы...";
    public static final String MISUNDERSTOOD = "Кажется, я не понял тебя. Может попросишь справку?";
}
