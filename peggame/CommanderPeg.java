package peggame;

import java.io.IOException;
import java.util.Scanner;

public class CommanderPeg {
    public static void playPegGame() {
        /**
         * command line commands
         *      help - displays commands
         *      move (r1 c1 r2 c2)
         *      check status
         *          displays end screen
         *      hint displays a possible moves
         *          return getPossible moves
         *          add to used hints set
         *      quit
         */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a file name: ");
        String filename = scanner.nextLine();
        PegGame game = null;
        try {
            game = PegReaderFile.PegFile(filename);
        }

        catch(IOException e) {
            System.out.println("file not found");
        }
        while (true) {
            System.out.println(game);
            
        }

    }
}
