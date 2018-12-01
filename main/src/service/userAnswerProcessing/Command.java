package src.service.userAnswerProcessing;

import src.service.quiz.QuizParsingException;

import java.util.List;

public enum Command {
    HELP("справка", AnswerProcessor::getHelp),
    QUIZ("викторина", AnswerProcessor::startQuiz),
    SCORE("счет", AnswerProcessor::getScore),
    EXIT("выход", AnswerProcessor::exit),
    TEXT("текст", AnswerProcessor::createText);

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

    public List<String> processCommand(AnswerProcessor answerProcessor) throws QuizParsingException {
        return commandProcessor.processCommand(answerProcessor);
    }

    interface CommandProcessor {
        List<String> processCommand(AnswerProcessor answerProcessor) throws QuizParsingException;
    }
}
