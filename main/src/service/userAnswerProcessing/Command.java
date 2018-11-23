package src.service.userAnswerProcessing;

import src.service.quiz.QuizParsingException;

import java.util.List;

public enum Command {
    HELP("справка", UserState::getHelp),
    QUIZ("викторина", UserState::startQuiz),
    SCORE("счет", UserState::getScore),
    EXIT("выход", UserState::exit),
    TEXT("текст", UserState::createText);

    private final String name;
    private final CommandProcessor commandProcessor;

    Command(String name, CommandProcessor commandProcessor) {
        this.name = name;
        this.commandProcessor = commandProcessor;
    }

    public static Command parse(String name) {
        Command[] values = values();

        for (Command command: values){
            if (command.name.equals(name)) {
                return command;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public List<String> processCommand(UserState userState) throws QuizParsingException {
        return commandProcessor.processCommand(userState);
    }

    interface CommandProcessor {
        List<String> processCommand(UserState userState) throws QuizParsingException;
    }
}
