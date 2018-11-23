package src.service.textGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TextGenerator {
    private static final int MINIMUM_SENTENCE_LENGTH = 5;
    private static final int TEXT_LENGTH = 3;
    private static final String END = "END";
    private static final Set<String> END_OF_SENTENCE_MARKERS = new HashSet<>(
            Arrays.asList(".", "?", "!"));

    private HashMap<String, WordFrequencyCounter> followingWords = new HashMap<>();

    TextGenerator(List<String> text) {
        String previousWord = END;

        for (String word: text){
            if (isEndOfSentence(word)){
                this.updateState(previousWord, word.substring(0, word.length() - 1));
                this.updateState(word.substring(0, word.length() - 1), END);
                previousWord = END;
            }
            else {
                this.updateState(previousWord, word);
                previousWord = word;
            }
        }
    }

    public static TextGenerator createTextGenerator(String textFileName) throws TextParsingException {
        List<String> text = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(textFileName));
            String line;

            while ((line = br.readLine()) != null) {
                String[] lineWords = line.split(" ");
                text.addAll(Arrays.asList(lineWords));
            }
        }
        catch (IOException ex) {
            throw new TextParsingException("Текст для обучения не найден!");
        }

        return new TextGenerator(text);
    }

    public List<String> createText() {
        List<String> text = new ArrayList<>();
        int count = TEXT_LENGTH;
        while (count != 0) {
            List<String> sentence = createSentence();
            if (sentence.size() < MINIMUM_SENTENCE_LENGTH) {
                continue;
            }
            text.add(String.join(" ", sentence));
            text.add(". ");
            count--;
        }
        return text;
    }

    private boolean isEndOfSentence(String word) {
        for (String token: END_OF_SENTENCE_MARKERS) {
            if (word.contains(token)) {
                return true;
            }
        }
        return false;
    }

    private void updateState(String word, String nextWord){
        if (followingWords.containsKey(word)){
            followingWords.get(word).update(nextWord);
        }
        else {
            WordFrequencyCounter counter = new WordFrequencyCounter();
            counter.update(nextWord);
            followingWords.put(word, counter);
        }
    }

    private List<String> createSentence() {
        String previousWord = END;
        List<String> result = new ArrayList<>();

        while (true){
            String word = this.followingWords.get(previousWord).getRandomWord();
            previousWord = word;

            if (word.equals(END)){
                break;
            }
            else {
                result.add(word);
            }
         }

        return result;
    }
}
