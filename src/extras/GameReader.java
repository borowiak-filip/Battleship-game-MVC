package extras;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// @borowiak-filip
/**
* GameReader object for parsing .txt file and implementing its data -> Ships 
*/
public class GameReader {

    public List<int[][]> readFile(String path) throws Exception {
        
        
        String[][] boardLayout = new String[11][11];
        String[] columns = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        
        int row = 0;

        // Create board layout

        // fill in columns 1-10
        boardLayout[0][0] = "/";
        for (int y = 1; y < boardLayout[0].length; y++) {
            boardLayout[y][0] = columns[y-1];
        }
        // fill in rows A - J
        for (int x = 1; x < boardLayout[1].length; x++) {
            boardLayout[0][x] = rows[x-1];
        }
        // fill in default layout
        for (int i = 1; i < boardLayout[0].length; i++) {
            for (int j = 1; j < boardLayout[1].length; j++) {

                boardLayout[i][j] = "o";
            }
        }

        int numberOfShips = 0; // store number of ships. Value read from the file, must be first line!
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String newline;
            
            while ((newline = reader.readLine()) != null) {
                // skip rows and columns 
                if (row == 0) {
                    int idx = newline.indexOf(":");
                        numberOfShips = Integer.parseInt(newline.substring(idx + 1).trim()); // get number of ships in the file
                        if (numberOfShips != 5) {
                                throw new IllegalArgumentException(Messages.SHIP_COUNT_EXCEPTION); // Game requirement
                        }
                    
                    row++; continue;
                }

                if (row == 1) {row++;continue;} // row 1 is empty, skip
                
                // ship parts of the same ship need to have the same id in the file!
                char[] line = newline.toCharArray(); // parse board row into array of chars
                for (int i = 1; i < line.length; i++) {
                    boardLayout[row-1][i] = String.valueOf(line[i]);   // loop through the array and mark ship number.
                }
                row++;  
            }  
            reader.close(); 
        
    
        
            List<int[][]> shipsCoords = new ArrayList<>();
            for (int z = 0; z < numberOfShips; z++) { // for all number of different ships in the game
                List<int[]> shipCoords = new ArrayList<>();
                for (int i = 1; i < boardLayout.length; i++) { // iterate through columns
                    for (int j = 1; j < rows.length+1; j++) { // iterate through rows
                        if(boardLayout[j][i].equals(String.valueOf(z+1))) { // check if this board till is a ship, if it is whether its part of the ship number z
                            int[] coord = {j, i}; // save its coordinates
                            shipCoords.add(coord);
                            
                        }
                        
                    }
                
                }
                int[][] tempcords = new int[shipCoords.size()][]; // temporarly store ship coordinates
                int count = 0;
                for (var coord : shipCoords) {
                    tempcords[count] = coord;
                    count++;
                }
                shipsCoords.add(tempcords);
            }

            int len5ShipCount = 0; int len4ShipCount = 0; int len3ShipCount = 0; int len2hipCount = 0; // init each ship instance counter

            // exctract number of parts (lenght) per ship
            for (int[][] coords : shipsCoords) {

                if (coords.length == 2) {len2hipCount++;}
                else if (coords.length == 3) {len3ShipCount++;}
                else if (coords.length == 4) {len4ShipCount++;}
                else if (coords.length == 5) {len5ShipCount++;}
            }

            // Check if required length of the ships is correct
            if (len2hipCount != 2 || len3ShipCount != 1 || len4ShipCount != 1 || len5ShipCount != 1) {
                throw new Exception(Messages.SHIP_LENGTH_EXCEPTION);
            }

            // Check if ships are adjacent, vertically or horizontally
            for (int[][] coords : shipsCoords) {
                if (!isHorizontallyAdj(coords) && !isVerticallyAdj(coords)) {
                    throw new Exception(Messages.SHIP_ADJACENT_EXCEPTION);
                }
            }
        return shipsCoords;
    }catch(IOException e) {throw new Exception(e.getMessage());}}

    // Check if the ship part is Horizontally adjacent
    public boolean isHorizontallyAdj(int[][] coords) {
        for (int i = 1; i < coords.length; i++) {
            if (coords[i][0] != coords[i - 1][0] || Math.abs(coords[i][1] - coords[i - 1][1]) != 1) {
                return false;
            }
        }
        return true;
    }

    // Check if the ship part is Vertically adjacent
    public boolean isVerticallyAdj(int[][] coords) {
        for (int i = 1; i < coords.length; i++) {
            if (coords[i][1] != coords[i - 1][1] || Math.abs(coords[i][0] - coords[i - 1][0]) != 1) {
                return false;
            }
        }
        return true;
    }
}


