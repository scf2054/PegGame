package peggame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import peggame.PegGame.GameState;

public class CommanderPeg {
    /**
     * Command input interface for prompting the user to play the game
     */
    public static void playPegGame(PegGame game) {
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
        Set<Move> hintsUsed = new HashSet<>();

        Location[] locations = game.getLocations();
        while (true) {
            System.out.println("\n" + game + "\n");
            System.out.print(">> ");
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            if (commands[0].equals("quit")) {
                System.out.print("Are you sure? (y/n): ");
                command = scanner.nextLine();
                if (command.equals("y")) {
                    System.out.println("Goodbye!");
                    break;
                } else if(command.equals("n")) {
                    continue;
                }
            }
            else if (commands[0].equals("help")) {
                System.out.println("Available commands:\n" +
                " help - displays this message\n" +
                " move r1 c1 r2 c2 - attempts to move a peg from r1 c1 to r2 c2 on the board.\n" +
                " hint - displays an available move.\n" +
                " solve - finishes the game if possible.\n" +
                " quit - quits the game");
            }

            else if (commands[0].equals("hint")) {
                // HashSet<Move> possible = (HashSet<Move>)game.getPossibleMoves();
                // ArrayList<Move> moves = new ArrayList<>();
                // for(Move possibility : possible) {
                //     moves.add(possibility);
                // }
                // if (hintsUsed.size() == moves.size()) {
                //     System.out.println("Used all of the hints");
                //     continue;
                // }
                // Random random = new Random();
                // boolean found = false;
                // while (!found) {
                //     int index = random.nextInt(moves.size());
                //     Move move = moves.get(index);
                //     if (!hintsUsed.contains(move)) {
                //         hintsUsed.add(move);
                //         System.out.println("hint: Move " + move);
                //         found = true;
                //     } 
                // }
                PegGameConfig solution = (PegGameConfig)PegGameConfig.getSolution(game);
                if(solution == null) {
                    System.out.println("This game is unwinnable. Have a nice day.");
                } else {
                    ArrayList<Move> movesMade = solution.getMovesMade();
                    System.out.println(movesMade.get(0));
                }
            }

            else if (commands[0].equals("move")) {
                int startRow, startCol, endRow, endCol;
                try {
                    startRow = Integer.parseInt(commands[1]);
                    startCol = Integer.parseInt(commands[2]);
                    endRow = Integer.parseInt(commands[3]);
                    endCol = Integer.parseInt(commands[4]);
                    Location startLoc = new Location(startRow, startCol);
                    Location endLoc = new Location(endRow, endCol);
                    boolean foundS = false;
                    boolean foundE = false;
                    for(Location loc : locations) {
                        if(loc.equals(startLoc)) {
                            startLoc = loc;
                            foundS = true;
                        } else if(loc.equals(endLoc)) {
                            endLoc = loc;
                            foundE = true;
                        }
                        if(foundS && foundE) {
                            hintsUsed = new HashSet<>();
                            break;
                        }
                    }
                    Move move = new Move(startLoc, endLoc);
                    try {
                        game.MakeMove(move);
                    }
    
                    catch (PegGameException e) {
                        System.out.println("Invalid Move.");
                    }
                    if (game.getGameState() == GameState.WON) {
                        System.out.println("You Win!");
                        System.out.println(game);
                        break;
                    }
    
                    else if (game.getGameState() == GameState.STALEMATE) {
                        System.out.println("No more moves");
                        System.out.println(game);
                        System.out.println("\nGoodbye!");
                        break;
                    }

                } catch(ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid move.");
                }
            }

            else {
                System.out.println("Invalid Input");
            }
        }
        scanner.close();

    }
}
