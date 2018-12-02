package src.service.textGenerator;

import org.apache.commons.io.IOUtils;
import src.service.IOManager;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static src.service.Constants.TEXT_FILE;

public class TextGenerator {
    private static final int MINIMUM_SENTENCE_LENGTH = 5;
    private static final int SENTENCE_COUNT = 3;
    private static final String END = "END";

    private Map<String, WordFrequencyCounter> followingWords = new ConcurrentHashMap<>();

    TextGenerator(List<String> text) {
        Pattern pattern = Pattern.compile(".*[a-zA-Z]+.*");

        for (String sentence : text){
            if (!pattern.matcher(sentence).matches()){
                continue;
            }

            addSentence(sentence);
        }
    }

    public static TextGenerator createTextGenerator(String textFileName) throws TextParsingException {
        String text;

        try{
            try (InputStream is = new FileInputStream(textFileName)) {
                text = IOUtils.toString(is, "UTF-8");
            }

        }
        catch (IOException e){
            throw new TextParsingException("Текст для обучения не найден!", e);
        }
        List<String> sentences = new ArrayList<>(Arrays.asList(text.split("[\\.\\!\\?]")));
        return new TextGenerator(sentences);
    }

    public static TextGenerator createTextGenerator() throws TextParsingException {
        return createTextGenerator(IOManager.getFilePath(TEXT_FILE));
    }

    public String createText() {
        return Stream.generate(this::createSentence)
                .filter(sentence -> sentence.size() >= MINIMUM_SENTENCE_LENGTH)
                .limit(SENTENCE_COUNT)
                .map(sentence -> String.join(" ", sentence) + ".")
                .collect(Collectors.joining(" "));
    }

    Map<String, Map<String, Integer>> getStatistics() {
        Map<String, Map<String, Integer>> result = new HashMap<>();

        for (Map.Entry<String, WordFrequencyCounter> entry: followingWords.entrySet()) {
            String key = entry.getKey();
            WordFrequencyCounter value = entry.getValue();
            result.put(key, value.getFrequencies());
        }

        return result;
    }

    private void updateState(String word, String nextWord){
        followingWords.computeIfAbsent(word, n -> new WordFrequencyCounter()).update(nextWord);
    }

    private void addSentence(String sentence) {
        String previousWord = END;
        for (String word: sentence.split(" ")){
            this.updateState(previousWord, word);
            previousWord = word;
        }
        this.updateState(previousWord, END);
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
