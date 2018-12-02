package src.service.textGenerator;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

class WordFrequencyCounter {
    private int totalWordsCount = 0;
    private Random random = ThreadLocalRandom.current();
    private Map<String, Integer> frequencies = new ConcurrentHashMap<>();

    void update(String word){
        frequencies.merge(word, 1, (a, b) -> a + b);
        totalWordsCount += 1;
    }

    String getRandomWord(){
        int countOfRandomWord = this.random.nextInt(this.totalWordsCount);
        for (String key: this.frequencies.keySet()){
            if (getWordCount(key) > countOfRandomWord){
                return key;
            }
            countOfRandomWord -= getWordCount(key);
        }
        return "";
    }

    Map<String, Integer> getFrequencies() { return frequencies; }

    private int getWordCount(String key){
        return frequencies.getOrDefault(key, 0);
    }
}
