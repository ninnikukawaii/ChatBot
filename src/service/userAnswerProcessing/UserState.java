package service.userAnswerProcessing;

import service.quiz.Question;
import service.quiz.Quiz;
import service.quiz.QuizParsingException;

import org.apache.commons.lang3.ArrayUtils;

class UserState {
    private UserStateType state;
    private Quiz quiz;
    private Question lastQuestion;

    private final String questionsFileName;

    UserState(UserStateType initialState, String questionsFileName) {
        state = initialState;
        this.questionsFileName = questionsFileName;
    }

    UserStateType getState(){ return state; }
    String[] getHelp() {
        return state.getHelp();
    }

    String[] getScore() {
        if (state == UserStateType.Quiz) {
            return new String[] {quiz.getScore()};
        }
        else return null;
    }

    String[] exit() {
        if (state == UserStateType.Quiz) {
            state = UserStateType.Chat;
            return new String[] {StandardResponse.QUIZ_FAREWELL};
        }
        else {
            state = UserStateType.Exit;
            return new String[] {StandardResponse.CHAT_FAREWELL};
        }
    }

    String[] checkAnswer(String answer) {
        if (state == UserStateType.Quiz){
            if (answer.equals(lastQuestion.getAnswer().toLowerCase())) {
                quiz.incrementCorrectAnswersCount();
                String[] response = new String[] {StandardResponse.CORRECT_ANSWER};
                return ArrayUtils.addAll(response, getNextQuestion());
            }
            else {
                String[] response = new String[] {StandardResponse.INCORRECT_ANSWER,
                        lastQuestion.getAnswer()};
                return ArrayUtils.addAll(response, getNextQuestion());
            }
        }
        else return null;
    }

    String[] startQuiz() throws QuizParsingException {
        quiz = Quiz.createQuiz(questionsFileName);
        updateState(UserStateType.Quiz);
        return ArrayUtils.addAll(StandardResponse.QUIZ_GREETING, getNextQuestion());
    }

    private void updateState(UserStateType state) {
        this.state = state;
    }

    private String[] getNextQuestion() {
        if (quiz.hasNextQuestion()) {
            lastQuestion = quiz.getNextQuestion();
            return new String[] {lastQuestion.getQuestion()};
        }
        else {
            updateState(UserStateType.Chat);
            return new String[] {StandardResponse.NO_MORE_QUESTIONS, StandardResponse.QUIZ_FAREWELL};
        }
    }
}
