package tests;

import console.Quiz;
import service.ItemsGenerator;
import service.Question;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.*;

public class QuestionGeneratorTest {

    private ItemsGenerator<Question> createGenerator() {
        ItemsGenerator<Question> generator = null;
        try {
            generator = new ItemsGenerator<>(Quiz.parseQuestions("questionsForTesting.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return generator;
    }

    @Test
    public void testGenerator() {
        ItemsGenerator<Question> generator = createGenerator();
        ArrayList<Question> questions = (ArrayList<Question>) generator.getAllItems();
        assertThat(questions, hasItem(new Question("Сколько будет 2х2?", "4")));
    }

    @Test
    public void testCountDecreases() {
        ItemsGenerator<Question> generator = createGenerator();
        int firstCount = generator.getCountOfItems();
        generator.iterator().next();
        int secondCount = generator.getCountOfItems();
        assertTrue(firstCount > secondCount);
    }
}