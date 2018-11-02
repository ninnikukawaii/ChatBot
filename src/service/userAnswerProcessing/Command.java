package service.userAnswerProcessing;

import org.apache.commons.lang3.ArrayUtils;
import service.quiz.QuizParsingException;

public enum Command {
    Help("справка") {

        @Override
        public String[] processCommand(UserState userState) {
            return userState.getHelp();
        }
    },
    Quiz("викторина"){

        @Override
        public String[] processCommand(UserState userState) throws QuizParsingException {
            return userState.createQuiz();
        }
    },
    Score("счет") {

        @Override
        public String[] processCommand(UserState userState) {
            return userState.getScore();
        }
    },
    Exit("выход") {

        @Override
        public String[] processCommand(UserState userState) {
            return userState.exit();
        }
    };

    private final String name;

    Command(String name) {
        this.name = name;
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
    public abstract String[] processCommand(UserState userState) throws QuizParsingException;
}
