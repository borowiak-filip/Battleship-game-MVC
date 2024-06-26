package model;

import interfaces.IShip;
import interfaces.IShipPart;

// @borowiak-filip
/**
Ship Object requires unique id, its length (how many ship parts), and its parts coordinates as an array of ints, where first position is coordX, and second is coordY   
*/
public class Ship implements IShip{

    private IShipPart[] shipParts; // lists of shipParts in a single Ship
    private int length; // how long is the ship? should be equal to IShipPart length


    public Ship( int length, int[][] shipPartCoordinates) {init(length, shipPartCoordinates);}

    @Override
    public int getLifeStatus() {
        int health = 0;
        for (IShipPart part : this.shipParts){if(part.getStatus()) {health++;} }
        return health;
    }


    @Override
    public int getLength() {return length;}

  
    @Override
    public IShipPart[] getShipParts() {return this.shipParts;}

    @Override
    public boolean checkShot(int coordX, int coordY) {

        boolean result = false;

        for (IShipPart shipPart : this.shipParts) { // loop through all ship parts and check if anything has been hit
            if(shipPart.getCoordX() == coordX && shipPart.getCoordY() == coordY && shipPart.getStatus())
            {
                shipPart.sunk(); // if yes, sank
                result = true;
                return result;

            }else{result = false;}
        }

        return result;
    }
    
    @Override
    public void init(int length, int[][] shipPartsCoordinates) {
        this.shipParts = new ShipPart[length];
        for (int x = 0; x < shipPartsCoordinates.length; x++) {
            IShipPart shipPart = new ShipPart(shipPartsCoordinates[x][0], shipPartsCoordinates[x][1]);
            this.shipParts[x] = shipPart;
        }
        this.length = length;
    }
    
}
