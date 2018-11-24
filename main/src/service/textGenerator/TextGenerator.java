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

    private Map<String, WordFrequencyCounter> followingWords = new HashMap<>();

    TextGenerator(List<String> text) {
        String previousWord = END;

        for (String word: text){
            if (isEndOfSentence(word)){
                word = word.toLowerCase();
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

    public String createText() {
        StringBuilder text = new StringBuilder();
        int count = TEXT_LENGTH;
        while (count != 0) {
            List<String> sentence = createSentence();
            if (sentence.size() < MINIMUM_SENTENCE_LENGTH) {
                continue;
            }
            text.append(String.join(" ", sentence)).append(". ");
            count--;
        }
        return text.toString();
    }

    Map<String, Map<String, Integer>> getStatistics() {
        Map<String, Map<String, Integer>> result = new HashMap<>();

        for (Map.Entry<String, WordFrequencyCounter> entry: followingWords.entrySet()) {
            String key = entry.getKey();
            WordFrequencyCounter value = entry.getValue();
            result.put(key, value.getStatistics());
        }

        return result;
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

    List<String> createSentence() {
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
