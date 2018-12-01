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
        if (frequencies.containsKey(word)){
            frequencies.put(word, frequencies.get(word) + 1);
        }
        else {
            frequencies.put(word, 1);
        }
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
        if (frequencies.containsKey(key)){
            return frequencies.get(key);
        }
        return 0;
    }
}
