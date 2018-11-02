package service.userAnswerProcessing;

import service.quiz.QuizParsingException;

import org.apache.commons.lang3.ArrayUtils;
import service.quiz.Question;
import service.quiz.Quiz;

public class AnswerProcessor {

    private UserState userState;
    private final String questionsFileName;
    private Quiz quiz;
    private Question lastQuestion;

    public AnswerProcessor(UserStateType initialState, String questionsFileName){
        this.userState = new UserState(initialState);
        this.questionsFileName = questionsFileName;
    }

    public UserStateType getUserState() {
        return userState.getState();
    }

    public String[] processAnswer(String answer) throws QuizParsingException {
        Command command = Command.parse(answer);
        UserStateType state = userState.getState();

        if (command == Command.Exit) {
            return userState.exit();
        }

        if (state == UserStateType.Quiz) {
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
        userState.updateState(UserStateType.Quiz);
    }

    private String[] getNextQuestion() {
        if (quiz.hasNextQuestion()) {
            lastQuestion = quiz.getNextQuestion();
            return new String[] {lastQuestion.getQuestion()};
        }
        else {
            userState.updateState(UserStateType.Chat);
            return new String[] {StandardResponse.NO_MORE_QUESTIONS, StandardResponse.QUIZ_FAREWELL};
        }
    }
}
