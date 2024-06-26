package interfaces;

import java.util.List;

import extras.Triple;

// @borowiak-filip

/**
* Battleship Board game Interface
*/
public interface IBoard {


    /**
     * This method allows to initialise the ships on the board.
     */
    public void initShips(List<IShip> ship);

   
     /**
     * This method checks if given coordinates (coordX, coordY) match object on the board.
     * ** Returns Triple object. 
     * @Return Triple object
     */
    public Triple checkTill(int coordX, int coordY);

    /**
     * This method returns user attempts in the game.
     */
    public int getCountVal(); // 

    /**
     * This method returns board as as a String array. 
     * @Return Board Layout
     */
    public String[][] getBoardLayout();

    /**
     * This method returns list of ships objects from the board.  
     * @Return List of Ship
     */
    public List<IShip> getShips();

    /**
     * This method checks if Game has finished. ** Returns boolean.
     */
    public boolean checkIfGameOver();

  
}
