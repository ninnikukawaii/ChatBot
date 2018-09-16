package console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        printDocument("greeting.txt");
        while (true) {
            String str = input.nextLine().toLowerCase();
            if (str.contains("справк")) {
                printDocument("help.txt");
            }
            else if (str.contains("викторин")) {
                printDocument("quizGreeting.txt");
                Quiz.startQuiz();
            }
            else if (str.equals("выход")) {
                break;
            }
            else {
                System.out.println("Кажется я вас не понял. Может вы попросите справку?");
            }
        }
        System.out.println("Приятно было пообщаться с тобой");
    }

    private static void printDocument(String filename)
    {
        filename = System.getProperty("user.dir") + "\\" + filename;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
