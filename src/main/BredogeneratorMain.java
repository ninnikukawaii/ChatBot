package main;

import bredogenerator.Dictionagram;
import service.IOManager;

public class BredogeneratorMain {
    public static void main(String[] args)  {
        Dictionagram dictionagram = new Dictionagram(IOManager.getFilePath("text.txt"));
        //dictionagram.print();
        System.out.println(dictionagram.makeSentance());
    }
}
