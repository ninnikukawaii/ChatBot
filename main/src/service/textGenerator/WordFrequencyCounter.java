package src.service.textGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class WordFrequencyCounter {
    private int totalWordsCount = 0;
    private Random random = new Random();
    private Map<String, Integer> frequencies = new HashMap<>();

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

    Map<String, Integer> getStatistics() { return frequencies; }

    private int getWordCount(String key){
        if (frequencies.containsKey(key)){
            return frequencies.get(key);
        }
        return 0;
    }
}
