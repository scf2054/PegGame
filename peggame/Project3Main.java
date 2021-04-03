package peggame;

import static org.junit.jupiter.api.DynamicTest.stream;

import java.io.IOException;
import java.util.Scanner;

public class Project3Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a file name: ");
        String filename = scanner.nextLine();
        PegGame game = null;
        try{
            game = ReadFile.TriangleFile(filename);
            CommanderPeg.playPegGame(game);
        } catch (IOException e){
            System.out.println("File not found.");
        }
        scanner.close();
    }
}
