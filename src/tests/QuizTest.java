package tests;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import service.quiz.Question;
import service.quiz.Quiz;
import service.quiz.QuizParsingException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static service.Constants.QUESTIONS_FILE;
import static service.Constants.TEST_QUESTIONS_FILE;

public class QuizTest {

    @Test
    public void testIncorrectFile(){
        assertThrows(QuizParsingException.class, () -> Quiz.createQuiz("incorrectFile"),
                "Файл вопросов не найден!");
    }

    @Test
    public void testHasNextQuestion() throws QuizParsingException {
        Quiz quiz = Quiz.createQuiz(TEST_QUESTIONS_FILE);
        assertTrue(quiz.hasNextQuestion());
        quiz.getNextQuestion();
        assertFalse(quiz.hasNextQuestion());
    }

    @Test
    public void testGetQuestion() throws QuizParsingException {
        Quiz quiz = Quiz.createQuiz(TEST_QUESTIONS_FILE);
        Question question = new Question("Сколько будет дважды два?", "четыре");
        Assertions.assertEquals(question.getAnswer(), quiz.getNextQuestion().getAnswer());
    }

    @Test
    public void testScore() throws QuizParsingException {
        Quiz quiz = Quiz.createQuiz(QUESTIONS_FILE);
        quiz.getNextQuestion();
        assertEquals("Твой счет: 0 из 0", quiz.getScore());
        quiz.incrementCorrectAnswersCount();
        quiz.getNextQuestion();
        assertEquals("Твой счет: 1 из 1", quiz.getScore());
    }
}
