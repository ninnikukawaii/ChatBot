package bredogenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Dictionagram {
    private HashMap<String, Dictogram> dictionagram = new HashMap<String, Dictogram>();

    public Dictionagram(String fileName){
        this.parseFile(fileName);
    }

    private void parseFile(String file){

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            String previousWord = "END";

            while ((line = br.readLine()) != null) {
                String[] lineWords = line.split(" ");

                for (String word: lineWords){
                    if (word.contains(".") || word.contains("?") || word.contains("!")){
                        this.update(previousWord, word.substring(0, word.length() - 1));
                        this.update(word.substring(0, word.length() - 1), "END");
                        previousWord = "END";
                    }
                    else {
                        this.update(previousWord, word);
                        previousWord = word;
                    }
                }
            }
        }
        catch (IOException ex) {
            System.out.println("file not found");
        }
    }

    private void update(String key, String word){
            if (this.dictionagram.containsKey(key)){
                this.dictionagram.get(key).update(word);
            }
            else {
                Dictogram dictogram = new Dictogram();
                dictogram.update(word);
                this.dictionagram.put(key, dictogram);
            }
    }

    public String makeSentance(){
        String previousWord = "END";
        StringBuilder result = new StringBuilder();

         while (true){
            String word = this.dictionagram.get(previousWord).getRandomWord();
            previousWord = word;

            if (word.equals("END")){
                result.append(".");
                break;
            }
            else {
                result.append(word);
                result.append(" ");
            }
         }
        return result.toString();
    }

    public void print(){ //Отладочная штука, мб пригодится в тестах
        for (String key: this.dictionagram.keySet()){
            System.out.println(key);
            this.dictionagram.get(key).Print();
        }
    }
}
