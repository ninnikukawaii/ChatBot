package service;

import service.enums.Command;
import service.exceptions.QuizParsingException;

import org.apache.commons.lang3.ArrayUtils;

public class AnswerProcessor {

    private UserState userState;
    private final String questionsFileName;
    private Quiz quiz;
    private Question lastQuestion;

    public AnswerProcessor(service.enums.UserState initialState, String questionsFileName){
        this.userState = new UserState(initialState);
        this.questionsFileName = questionsFileName;
    }

    public service.enums.UserState getUserState() {
        return userState.getState();
    }

    public String[] processAnswer(String answer) throws QuizParsingException {
        Command command = Command.parse(answer);
        service.enums.UserState state = userState.getState();

        if (command == Command.Exit) {
            return userState.exit();
        }

        if (state == service.enums.UserState.Quiz) {
            if (command == Command.Help) {
                return StandardResponse.QUIZ_HELP;
            }
            else if (answer.equals(lastQuestion.getAnswer().toLowerCase())) {
                quiz.incrementCorrectAnswersCount();
                String[] response = new String[] {StandardResponse.CORRECT_ANSWER};
                return ArrayUtils.addAll(response, getNextQuestion());
            }
            else if (command == Command.Score) {
                return new String[] {quiz.getScore()};
            }
            else {
                String[] response = new String[] {StandardResponse.INCORRECT_ANSWER,
                        lastQuestion.getAnswer()};
                return ArrayUtils.addAll(response, getNextQuestion());
            }
        }
        else {
            if (command == Command.Help) {
                return StandardResponse.CHAT_HELP;
            }
            else if (command == Command.Quiz) {
                createQuiz();
                lastQuestion = quiz.getNextQuestion();
                return ArrayUtils.addAll(StandardResponse.QUIZ_GREETING, lastQuestion.getQuestion());
            }
            else {
                return new String[] {StandardResponse.MISUNDERSTOOD};
            }
        }
    }

    private void createQuiz() throws QuizParsingException {
        quiz = Quiz.createQuiz(questionsFileName);
        userState.updateState(service.enums.UserState.Quiz);
    }

    private String[] getNextQuestion() {
        if (quiz.hasNextQuestion()) {
            lastQuestion = quiz.getNextQuestion();
            return new String[] {lastQuestion.getQuestion()};
        }
        else {
            userState.updateState(service.enums.UserState.Chat);
            return new String[] {StandardResponse.NO_MORE_QUESTIONS, StandardResponse.QUIZ_FAREWELL};
        }
    }
}
