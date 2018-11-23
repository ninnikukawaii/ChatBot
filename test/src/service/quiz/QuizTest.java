package src.service.quiz;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import src.service.quiz.ItemsGenerator;
import src.service.quiz.Question;
import src.service.quiz.Quiz;
import src.service.quiz.QuizParsingException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static src.service.Constants.QUESTIONS_FILE;

public class QuizTest {

    private List<Question> testQuestionList = new ArrayList<Question>() {{
        add( new Question("Сколько будет дважды два?", "четыре"));}};

    @Test
    public void testIncorrectFile(){
        assertThrows(QuizParsingException.class, () -> Quiz.createQuiz("incorrectFile"),
                "Файл вопросов не найден!");
    }

    @Test
    public void testHasNextQuestion() {
        Quiz quiz = new Quiz(testQuestionList);
        assertTrue(quiz.hasNextQuestion());
        quiz.getNextQuestion();
        assertFalse(quiz.hasNextQuestion());
    }

    @Test
    public void testGetQuestion() {
        Quiz quiz = new Quiz(testQuestionList);
        Question question = new Question("Сколько будет дважды два?", "четыре");
        Assertions.assertEquals(question, quiz.getNextQuestion());
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

    @Test
    public void testGeneratorCountDecreases() {
        ItemsGenerator<Question> generator = new Quiz(testQuestionList).getQuestionGenerator();
        int firstCount = generator.getCountOfItems();
        generator.iterator().next();
        int secondCount = generator.getCountOfItems();
        assertTrue(firstCount > secondCount);
    }
}
