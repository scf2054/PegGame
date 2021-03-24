package peggame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    
    /**
     * Makes the board for the user to play the game. Reads in the file
     * and depending on where the pegs ("o") are, the board places a peg there
     * from the beginning. Leaves in spaces (".").
     * 
     * @param filename for the board the user wants to use
     * 
     * @return PegGame of type rectangle for the user to play
     * 
     * @throws IOException for the reader variables
     */
    public static PegGame PegFile(String filename) throws IOException{
        FileReader filereader = new FileReader(filename);
        BufferedReader reader = new BufferedReader(filereader);

        int rows = Integer.parseInt(reader.readLine());
        int index = 0;

        String line = reader.readLine();
        String[] split = line.split("");
        int cols = split.length;
        
        PegGame peg = new RectangularBoard(rows, cols);
        Location[] locations = peg.getLocations();

        for(String symbol : split){
            if(symbol.equals("o")){
                locations[index].setPeg(true);
            }
            index++;
        }

        for(line = reader.readLine(); line != null; line = reader.readLine()){
            split = line.split("");
            for(String symbol : split){
                if(symbol.equals("o")){
                    locations[index].setPeg(true);
                }
                index++;
            }
        }
        reader.close();
        filereader.close();
        return peg;
    }

    public static void main(String[] args) {
        String filename = "data/10_10.txt";

        try {
            PegGame peg = PegFile(filename);
            System.out.println(peg);

        } catch (IOException e){
            System.out.println("File doesn't exist, you turnip.");
        }
    }
}
