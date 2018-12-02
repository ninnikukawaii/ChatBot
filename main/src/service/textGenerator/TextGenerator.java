package src.service.textGenerator;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static src.service.Constants.TEXT_FILE;

public class TextGenerator {
    private static final int MINIMUM_SENTENCE_LENGTH = 5;
    private static final int SENTENCE_COUNT = 3;
    private static final String END = "END";

    private Map<String, WordFrequencyCounter> followingWords = new ConcurrentHashMap<>();

    TextGenerator(List<String> text) {
        for (String sentence : text){
            if (sentence.toLowerCase().equals(sentence)){
                continue;
            }
            String previousWord = END;
            for (String word: sentence.split(" ")){
                this.updateState(previousWord, word.toLowerCase());
                previousWord = word.toLowerCase();
            }
            this.updateState(previousWord, END);
        }
    }


    public static TextGenerator createTextGenerator(String textFileName) {
        String tex = "";

        try{
            InputStream is = new FileInputStream(textFileName);
            try //Чет не понимаю как исправить
            {
                tex = IOUtils.toString(is, "UTF-8");
            }
            finally { is.close(); }

        }
        catch (IOException e){
            System.out.println("Ошибка отсутвия файла");
        }
        List<String> text = new ArrayList<>(Arrays.asList(tex.split("[\\.\\!\\?]")));
        return new TextGenerator(text);
    }

    public static TextGenerator createTextGenerator() {
        return createTextGenerator(TEXT_FILE);
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
