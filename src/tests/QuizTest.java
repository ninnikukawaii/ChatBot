package tests;

import org.junit.Test;
import service.Question;
import service.Quiz;
import service.QuizParsingException;

import java.util.ArrayList;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class QuizTest {
    private Quiz quiz;

    @Test
    public void TestOnFalseFile(){
        assertThrows(quiz.parseQuestions("FileFor"), new QuizParsingException("Ошибка в файле вопросов!"));
    }

    @Test
    public void TestOnListQuestion(){
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("Сколько будет 2х2?", "4"));
        assertEquals(quiz.parseQuestions("FileForTesting"), questions);
    }

    @Test
    public void TestOnCountQuestions(){
        quiz.parseQuestions("FileForTesting");
        assertTrue(quiz.hasNextQuestion());
        quiz.getNextQuestion();
        assertFalse(quiz.hasNextQuestion());
    }

    @Test
    public void  TestOnQuestion(){
        quiz.parseQuestions("FileForTesting");
        assertEquals(quiz.getNextQuestion(), new Question("Сколько будет 2х2?", "4"));
    }

    @Test void TestOnCorrectScore(){
        quiz.parseQuestions("FileForTesting");
        quiz.getNextQuestion();
        assertEquals(quiz.getScore(), "Твой счет: 0 из 0");
        quiz.incrementCorrectAnswersCount();
        quiz.getNextQuestion();
        assertEquals(quiz.getScore(), "Твой счет: 1 из 1");

    }
}
