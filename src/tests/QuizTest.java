package tests;

import org.junit.Test;
import service.Question;
import service.Quiz;
import service.exceptions.QuizParsingException;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        quiz.getNextQuestion();
        assertFalse(quiz.hasNextQuestion());
    }

    @Test
    public void testGetQuestion() throws QuizParsingException {
        Quiz quiz = Quiz.createQuiz("questionsForTesting.txt");
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("Сколько будет дважды два?", "четыре"));
        questions.add(new Question("Столица Великобритании?", "Лондон"));
        Question question = quiz.getNextQuestion();
        assertThat(questions, hasItem(question));
    }

    @Test
    public void testScore() throws QuizParsingException {
        Quiz quiz = Quiz.createQuiz("questionsForTesting.txt");
        quiz.getNextQuestion();
        assertEquals("Твой счет: 0 из 0", quiz.getScore());
        quiz.incrementCorrectAnswersCount();
        quiz.getNextQuestion();
        assertEquals("Твой счет: 1 из 1", quiz.getScore());
    }
}
