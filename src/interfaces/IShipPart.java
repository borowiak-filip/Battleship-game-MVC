package interfaces;

// @borowiak-filip
/**
* Ship Interface
*/
public interface IShipPart {
    /**
     * This method returns if ShipPart has been sunk. ** Returns boolean
     * @return health of the shipPart as (boolean)
     */
    public boolean getStatus();

    /**
     * This method returns ShipPart coordinate X
     * @return ShipPart coordinates X as (int)
     */
    public int getCoordX();

    /**
     * This method returns ShipPart coordinate y
     * @return ShipPart coordinates Y as (int)
     */
    public int getCoordY();

    /**
     * This method sets the status of the ShipPart to sunk
     */
    public void sunk();
}