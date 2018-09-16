package console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Printdocument("greeting.txt");
        String str = "";
        loop: while (!str.equals("выход"))
        {
            str = input.nextLine().toLowerCase();
            if (str.contains("справк"))
            {
                Printdocument("reference.txt");
            }
            else if (str.contains("викторин"))
            {
                Quiz.Quiz();
                break loop;
            }
            else {
                System.out.println("Кажется я вас не понял. Может вы попросите справку?");
            }
        }
        System.out.println("Приятно было пообщаться с тобой");
    }

    public static void Printdocument(String filename)
    {
        String nameOfFile = System.getProperty("user.dir") + "\\" + filename;
        try (BufferedReader br = new BufferedReader(new FileReader(nameOfFile)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
