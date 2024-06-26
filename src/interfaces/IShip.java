package interfaces;

// @borowiak-filip

/**
* Ship Interface
*/
public interface IShip {

    /**
     * This method initialise Ship object.
     */
    public void init(int length, int[][] getShipPartsCoordinates);


    /**
     * This method returns what is the remaining health of the Ship (not sunk parts).
     * @return health of the ship as (int)
     */
    public int getLifeStatus();

    /**
     * This method returns vertical or horizontal length of the Ship.
     * @return parts count of of the ship as (int)
     */
    public int getLength();

   
    /**
     * This method returns an array of ShipParts (ShipPart).
     * @return ShipParts from a specific Ship
     */
    public IShipPart[] getShipParts();

    /**
     * This method invokes checking if Ship has been hit.
     * @return whether user action hit the ship as (boolean)
     */
    public boolean checkShot(int coordX, int coordY);

    
    
}
