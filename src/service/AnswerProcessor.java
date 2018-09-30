package service;

import console.Quiz;
import service.enums.Command;
import service.enums.UserState;

import java.io.*;

public class AnswerProcessor {

    private UserState userState;
    private String questionsFileName;
    private Quiz quiz;
    private Question lastQuestion;

    public AnswerProcessor(UserState userState, String questionsFileName){
        this.userState = userState;
        this.questionsFileName = questionsFileName;
    }

    public UserState getUserState() {
        return userState;
    }

    public String processAnswer(String answer) throws IOException {

        if (userState == UserState.Quiz && answer.equals(lastQuestion.getAnswer().toLowerCase())) {
            quiz.incrementCorrectAnswersCount();
            return Response.correctAnswer + getNextQuestion();
        }
        else if (userState == UserState.Quiz && Command.parse(answer) == Command.Exit) {
            userState = UserState.Chat;
            return Response.quizFarewell;
        }
        else if (userState == UserState.Chat && Command.parse(answer) == Command.Exit) {
            userState = UserState.Exit;
            return Response.chatFarewell;
        }
        else if (userState == UserState.Quiz && Command.parse(answer) == Command.Score) {
            return quiz.getScore();
        }
        else if (Command.parse(answer) == Command.Help) {
            return Response.help;
        }
        else if (userState == UserState.Chat && Command.parse(answer) == Command.Quiz) {
            createQuiz();
            lastQuestion = quiz.getNextQuestion();
            return Response.quizGreeting + "\n\n" + lastQuestion.getQuestion();
        }
        else if (userState == UserState.Quiz) {
            return Response.incorrectAnswer + lastQuestion.getAnswer() + getNextQuestion();
        }
        else {
            return Response.misunderstood;
        }
    }

    private void createQuiz() throws IOException {
        quiz = new Quiz(questionsFileName);
        userState = UserState.Quiz;
    }

    private String getNextQuestion() {
        if (quiz.hasNextQuestion()) {
            lastQuestion = quiz.getNextQuestion();
            return "\n\n" + lastQuestion.getQuestion();
        }
        else {
            userState = UserState.Chat;
            return "\n\n" + Response.noMoreQuestions + "\n\n" + Response.quizFarewell;
        }
    }
}
