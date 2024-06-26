package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import extras.Messages;
import extras.Triple;
import interfaces.IBoard;
import interfaces.IShip;

// @borowiak-filip
/**
* GameModel represents Model part in the MVC pattern. It holds Board object and allows for communication with Controller 
*/
@SuppressWarnings("deprecation") // Added to disable Observale -- Observer deprecation notification
public class GameModel extends Observable {

    private IBoard board; // store an instance of the Board object
    
    public GameModel() {IBoard board = new Board(); this.board = board;}

    public GameModel getGameModel() {return this;}

    /**
     * This method returns Board object
     * @return boardLayout (String[][])
     */
    public String[][] getBoardLayout() {return this.board.getBoardLayout();}

    /**
     * This method invokes user's action in the actual objects
     * @return Triple object
     */
    public Triple change(int coordX, int coordY) {
        Triple results = this.board.checkTill(coordX, coordY);
        setChanged();
        String message = results.getSecondValue();
        notifyObservers(message);
        return results;
        
    }

    /**
     * This method checks if field user wants to access is valid
     * @return boolean value
     */
    public boolean isValid(int coordX, int coordY) {
        if (coordX >= 1 && coordX <= 10 && coordY >= 1 && coordY <= 10){
            return true;} else {return false;}
        
    }

    /**
     * This method checks if field user wants to access is valid
     * @return boolean value
     */
    public boolean hasBeenHitBefore(int coordX, int coordY) {
        if (!this.getBoardLayout()[coordX][coordY].equals("-")){return true;} else {return false;} // check if its an empty field
    }

    /**
     * This method inkoves GameModel initialisation -> initShips(shipsCoords).
     */
    public void initialize(List<int[][]> shipsCoords) {

        initShips(shipsCoords); // Create Ships
        setChanged(); // action has been make, there is a change
        notifyObservers(); // invoke actions
    }

    /**
     * This method initialise Ships on the Board.
     */
    public void initShips(List<int[][]> shipsCoords) {
        List<IShip> ships = new ArrayList<IShip>(); // init Ships storage
        int x = 1; // Ship Ids start from 1, hence set to 1
        for (int[][] shipCoord : shipsCoords) {
            IShip ship = new Ship(shipCoord.length, shipCoord); // For each set of coordinates create a Ship object
            ships.add(ship); // add ship to the main storage
            System.out.println(Messages.NEW_SHIP + x + "\n");
            x++;
        }
        this.board.initShips(ships); // Place ship on the board
    }

    /**
     * This method checks if Game has finished.
     * @return boolean
     */
    public boolean checkIfGameOver() {return this.board.checkIfGameOver();}

    /**
     * This method returns user attempts in the game.
     * @return int
     */
    public int getCountVal() {return this.board.getCountVal();}

    
  
}
