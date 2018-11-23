package bredogenerator;

import java.util.HashMap;
import java.util.Random;

public class Dictogram {
    //private int types = 0; // Число уникальных ключей в распределении
    private int token = 0; // общее количество всех слов в распределении
    private Random random = new Random();
    private HashMap<String, Integer> dictogram = new HashMap<String, Integer>();

    public void update(String word){
        if (this.dictogram.containsKey(word)){
            this.dictogram.put(word, this.dictogram.get(word) + 1);
        }
        else {
            this.dictogram.put(word, 1);
            //this.types += 1;
        }
        this.token += 1;
    }

    private int count(String key){
        if (this.dictogram.containsKey(key)){
            return this.dictogram.get(key);
        }
        return 0;
    }

    public String getRandomWord(){

        String randomWord = "";
        int countOfRandomWord = this.random.nextInt(this.token);
        for (String key: this.dictogram.keySet()){
            if (count(key) > countOfRandomWord){
                randomWord = key;
                break;
            }
            countOfRandomWord -= count(key);
        }
        return randomWord;
    }

    public void Print(){
        for (String key: this.dictogram.keySet()) {
            System.out.print(key + " : " + this.dictogram.get(key) + " ");
        }
        //System.out.print(this.token + " ");
        //System.out.print(this.types);
    }
}
