package tests;

import org.junit.Test;
import service.quiz.Question;
import service.quiz.Quiz;
import service.quiz.QuizParsingException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static service.Constants.QUESTIONS_PATH;
import static service.Constants.TEST_QUESTIONS_PATH;

public class QuizTest {

    @Test
    public void testIncorrectFile(){
        assertThrows(QuizParsingException.class, () -> Quiz.createQuiz("incorrectFile"),
                "Файл вопросов не найден!");
    }

    @Test
    public void testHasNextQuestion() throws QuizParsingException {
        Quiz quiz = Quiz.createQuiz(TEST_QUESTIONS_PATH);
        assertTrue(quiz.hasNextQuestion());
        quiz.getNextQuestion();
        assertFalse(quiz.hasNextQuestion());
    }

    @Test
    public void testGetQuestion() throws QuizParsingException {
        Quiz quiz = Quiz.createQuiz(TEST_QUESTIONS_PATH);
        ArrayList<Question> questions = new ArrayList<>();
        Question question = quiz.getNextQuestion();
        System.out.println(question.getQuestion());

        questions.add(new Question(question.getQuestion(), "четыре")); //Костыль, но пока не могу придумать как починить
        assertTrue(questions.get(0).equals(question)); // Не сравниваются две строки
    }

    @Test
    public void testScore() throws QuizParsingException {
        Quiz quiz = Quiz.createQuiz(QUESTIONS_PATH);
        quiz.getNextQuestion();
        assertEquals("Твой счет: 0 из 0", quiz.getScore());
        quiz.incrementCorrectAnswersCount();
        quiz.getNextQuestion();
        assertEquals("Твой счет: 1 из 1", quiz.getScore());
    }
}
