package model;

import interfaces.IShipPart;

// @borowiak-filip
public class ShipPart implements IShipPart {

    private boolean alive; // Ship health true - alive, false - down
    private int coordX; // rows position on the map 1-10
    private int coordY; // cols position on the map 1-10

    @Override
    public boolean getStatus() {return alive;}

    @Override
    public int getCoordX() {return this.coordX;}

    @Override
    public int getCoordY() {return this.coordY;}

    @Override
    public void sunk() {this.alive = false;}

    public ShipPart(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.alive = true;
    }

}
