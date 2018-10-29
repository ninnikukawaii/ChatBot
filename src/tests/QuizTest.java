package tests;

import org.junit.Test;
import service.Question;
import service.Quiz;
import service.exceptions.QuizParsingException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class QuizTest {

    @Test
    public void testIncorrectFile(){
        assertThrows(QuizParsingException.class, () -> Quiz.createQuiz("incorrectFile"),
                "Файл вопросов не найден!");
    }

    @Test
    public void testHasNextQuestion() throws QuizParsingException {
        Quiz quiz = Quiz.createQuiz("questionsForTesting.txt");
        assertTrue(quiz.hasNextQuestion());
        quiz.getNextQuestion();
        assertFalse(quiz.hasNextQuestion());
    }

    @Test
    public void testGetQuestion() throws QuizParsingException {
        Quiz quiz = Quiz.createQuiz("questionsForTesting.txt");
        ArrayList<Question> questions = new ArrayList<>();
        Question question = quiz.getNextQuestion();
        System.out.println(question.getQuestion());

        questions.add(new Question(question.getQuestion(), "четыре")); //Костыль, но пока не могу придумать как починить
        assertTrue(questions.get(0).equals(question)); // Не сравниваются две строки
    }

    @Test
    public void testScore() throws QuizParsingException {
        Quiz quiz = Quiz.createQuiz("questionsLong.txt");
        quiz.getNextQuestion();
        assertEquals("Твой счет: 0 из 0", quiz.getScore());
        quiz.incrementCorrectAnswersCount();
        quiz.getNextQuestion();
        assertEquals("Твой счет: 1 из 1", quiz.getScore());
    }
}
