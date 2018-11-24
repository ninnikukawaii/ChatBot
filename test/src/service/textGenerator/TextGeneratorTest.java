package src.service.textGenerator;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TextGeneratorTest {

    private List<String> text = Arrays.asList("One", "fish", "two", "fish",
            "red", "fish", "blue", "fish.");
    private TextGenerator textGenerator = new TextGenerator(text);

    @Test
    public void testStatistics() {
        assertEquals(getExpectedStatistics(), textGenerator.getStatistics());
    }

    @Test
    public void testCreateSentence(){
        Set<String> words = getExpectedStatistics().keySet();
        for (String word: textGenerator.createSentence()) {
            assertTrue(words.contains(word));
        }
    }

    @Test
    public void testFirstWord() {
        assertEquals("One", textGenerator.createSentence().get(0));
    }

    @Test
    public void testLastWord() {
        List<String> sentence = textGenerator.createSentence();
        assertEquals("fish", sentence.get(sentence.size() - 1));
    }

    private Map<String, Map<String, Integer>> getExpectedStatistics() {
        HashMap<String, Map<String, Integer>> result = new HashMap<>();

        result.put("One", new HashMap<>());
        result.get("One").put("fish", 1);
        result.put("two", new HashMap<>());
        result.get("two").put("fish", 1);
        result.put("red", new HashMap<>());
        result.get("red").put("fish", 1);
        result.put("blue", new HashMap<>());
        result.get("blue").put("fish", 1);

        result.put("fish", new HashMap<>());
        result.get("fish").put("two", 1);
        result.get("fish").put("red", 1);
        result.get("fish").put("blue", 1);
        result.get("fish").put("END", 1);

        result.put("END", new HashMap<>());
        result.get("END").put("One", 1);

        return result;
    }
}
