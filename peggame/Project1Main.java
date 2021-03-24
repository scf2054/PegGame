package peggame;

import java.io.IOException;
import java.util.Scanner;

public class Project1Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a file name: ");
        String filename = scanner.nextLine();
        PegGame game = null;
        try{
            game = ReadFile.PegFile(filename);
        } catch (IOException e){
            System.out.println("H");
        }
        CommanderPeg.playPegGame(game);
        scanner.close();
    }
}
