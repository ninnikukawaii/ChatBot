package service;

import service.enums.Command;
import service.exceptions.QuizParsingException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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

        if (command == Command.Help) {
            return new String[] {Response.help};
        }
        else if (command == Command.Exit) {
            return userState.exit();
        }

        if (state == service.enums.UserState.Quiz) {
            if (answer.equals(lastQuestion.getAnswer().toLowerCase())) {
                quiz.incrementCorrectAnswersCount();
                ArrayList<String> result = new ArrayList<>();
                result.add(Response.correctAnswer);
                result.addAll(Arrays.asList(getNextQuestion()));
                return result.toArray(new String[0]);
            }
            else if (command == Command.Score) {
                return new String[] {quiz.getScore()};
            }
            else {
                String[] response = new String[] {Response.incorrectAnswer, lastQuestion.getAnswer()};
                ArrayList<String> result = new ArrayList<>(Arrays.asList(response));
                result.addAll(Arrays.asList(getNextQuestion()));
                return result.toArray(new String[0]);
            }
        }
        else {
            if (command == Command.Quiz) {
                createQuiz();
                lastQuestion = quiz.getNextQuestion();
                return new String[] {Response.quizGreeting, lastQuestion.getQuestion()};
            }
            else {
                return new String[] {Response.misunderstood};
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
            return new String[] {Response.noMoreQuestions, Response.quizFarewell};
        }
    }
}
