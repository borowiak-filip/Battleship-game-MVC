package model;

import java.util.List;
import extras.Triple;
import extras.Messages;
import extras.ShipStatus;
import interfaces.IShip;
import interfaces.IShipPart;
import interfaces.IBoard;

// @borowiak-filip
/**
* Board object represents 11x11 Battleship gird. It consits of boardLayout - plain layout of the map,
* and boardLayoutMemory - game layout which represents players active grid. Board holds most of the game logic.
*/
public class Board implements IBoard{

    private String[][] boardLayout = new String[][]{
        {"/", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"},
        {"1", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
        {"2", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
        {"3", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
        {"4", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
        {"5", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
        {"6", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
        {"7", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
        {"8", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
        {"9", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
        {"10","-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}}; // plain layout

    private List<IShip> ships;
    protected int tries;

    @Override
    public Triple checkTill(int coordX, int coordY) {
        
        increaseCount(); // note user attempt
        String hit = Messages.MISSED_SHOT;
        int[] coordinates = {coordX, coordY}; // Pack values in Triple-require format
        Triple returnValues = new Triple(coordinates, hit, null); // init return Value
        
        for (IShip ship : this.ships) {
            if (ship.getLifeStatus() > 0){
                boolean result = ship.checkShot(coordX, coordY); // check for each ShipPart in Ship if it has been hit
                if (result) {
                    hit = Messages.GOOD_SHOT;
                    
                    returnValues.setSecondValue(hit); // pack message
                    this.boardLayout[coordX][coordY] = ShipStatus.H.toString(); // set board to Hit tag "H"
                    if (ship.getLifeStatus() <= 0) {
                        hit = Messages.SUNK_SHIP;
                        
                        returnValues.setSecondValue(hit); // override return message 
                        int[][] sinkedCoords = new int[ship.getLength()][2];
                        int counter = 0;
                        for (IShipPart part : ship.getShipParts()) {

                            sinkedCoords[counter][0] = part.getCoordX();
                            sinkedCoords[counter][1] = part.getCoordY();
                            counter++;

                        }
                        returnValues.setThirdValue(sinkedCoords);


                        // Mark Hs as Xs if ship has sunk = For CLI 
                        for (IShipPart part: ship.getShipParts()) {this.boardLayout[part.getCoordX()][part.getCoordY()] = ShipStatus.X.toString();}
                    }
                    break;
                }
                else { this.boardLayout[coordX][coordY] = ShipStatus.M.toString();} // set board to Missed tag "M"
            }
          
        }
        return returnValues;
    }
    
    @Override
    public String[][] getBoardLayout() {return this.boardLayout;}

    @Override
    public int getCountVal(){return this.tries;}
    
    @Override
    public boolean checkIfGameOver() {

        int allShips = this.ships.size(); // get how many ships there is in total
        int deadShipsCounter = 0; // initialise counter of destroyed ships to 0. 
        
        // loop through ships and mark those that has been destroyed.
        for (IShip ship : this.ships) {
            if (ship.getLifeStatus() <= 0) {deadShipsCounter ++;} // Ship is destroyed if all its parts are destroyed! 
        }
        if (deadShipsCounter == allShips) {return true;} // if all Ships are down = GameOver
        return false;
    }

    @Override
    public void initShips(List<IShip> ships) {this.ships = ships;}

    @Override
    public List<IShip> getShips() {return this.ships;}

    private void increaseCount() {this.tries++;} // Note user attempt
 
}
