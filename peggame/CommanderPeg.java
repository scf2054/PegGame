package peggame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import peggame.PegGame.GameState;

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
        Set<Move> hintsUsed = new HashSet<>();
        try {
            game = ReadFile.PegFile(filename);
        }

        catch(IOException e) {
            System.out.println("file not found");
        }
        while (true) {
            System.out.println(game);
            System.out.print(">> ");
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            if (commands[0].equals("quit")) {
                System.out.print("Are you sure(y/n): ");
                command = scanner.nextLine();
                if (command.equals("y")) {
                    System.out.println("GoodBye!");
                    break;
                }
            }
            else if (commands[0].equals("help")) {
                System.out.println("Available commands:\n" +
                " help - displays this message\n" +
                " move r1 c1 r2 c2 - attempts to move a peg from r1 c1 to r2 c2 on the board.\n" +
                " hint - displays an available move.\n" +
                " quit - quits the game");
            }

            else if (commands[0].equals("hint")) {
                ArrayList<Move> moves = (ArrayList<Move>)game.getPossibleMoves();
                Random random = new Random();
                boolean found = false;
                while (!found) {
                    int index = random.nextInt(moves.size());
                    Move move = moves.get(index);
                    if (!hintsUsed.contains(move)) {
                        hintsUsed.add(move);
                        System.out.println("Move " + move);
                        found = true;
                    } 
                }
            }

            else if (commands[0].equals("move")) {
                int startRow = Integer.parseInt(commands[1]);
                int startCol = Integer.parseInt(commands[2]);
                int endRow = Integer.parseInt(commands[3]);
                int endCol = Integer.parseInt(commands[4]);
                Location startLoc = new Location(startRow, startCol);
                Location endLoc = new Location(endRow, endCol);
                Move move = new Move(startLoc, endLoc);
                try {
                    game.MakeMove(move);
                }

                catch (PegGameException e) {
                    System.out.println("Invalid Move.");
                }
                if (game.getGameState() == GameState.WON) {
                    System.out.println("You Won!");
                    System.out.println(game);
                    break;
                }

                else if (game.getGameState() == GameState.STALEMATE) {
                    System.out.println("No more moves");
                    System.out.println(game);
                    break;
                }
            }

            else {
                System.out.println("Invalid Input");
            }
        }

    }
}
