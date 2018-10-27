package tests;

import service.Quiz;
import service.ItemsGenerator;
import service.Question;
import service.exceptions.QuizParsingException;

import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.*;

public class QuestionGeneratorTest {

    private ItemsGenerator<Question> createGenerator() throws QuizParsingException {
        return Quiz.createQuiz("questionsForTesting.txt").getQuestionGenerator();
    }

    @Test
    public void testGenerator() throws QuizParsingException {
        ItemsGenerator<Question> generator = createGenerator();
        ArrayList<Question> questions = (ArrayList<Question>) generator.getAllItems();
        Question question = new Question("Столица Великобритании?", "Лондон");
        assertThat(questions, hasItem(question));
    }

    @Test
    public void testCountDecreases() throws QuizParsingException {
        ItemsGenerator<Question> generator = createGenerator();
        int firstCount = generator.getCountOfItems();
        generator.iterator().next();
        int secondCount = generator.getCountOfItems();
        assertTrue(firstCount > secondCount);
    }
}