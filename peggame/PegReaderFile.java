package peggame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PegReaderFile {
    
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
}
