package extras;
// @borowiak-filip

/**
 * Triple object allowing to store 3 values(int[] - coordinates of the filed user tried to hit, String message, int[] coordinates of all parts of the ship if sank   
 */
public class Triple {

    private int[] shipCoordinates;
    private String message;
    private int[][] sankCoordinates;

    /**
     * Triple object storing 3 values (Ship, String, int[])  
     */
    public Triple(int[] shipCoordinates, String message, int[][] sankedCoordinates) {
        
        
        this.shipCoordinates = shipCoordinates;
        this.message = message;
        this.sankCoordinates = sankedCoordinates;

    }
    /**
     * this method allows to set the Ship object
     */
    public void setFirstValue(int[] shipCoordinates) {
        this.shipCoordinates = shipCoordinates;
    }
    /**
     * this method allows to set the message value
     */
    public void setSecondValue(String message) {
        this.message = message;
    }
    /**
     * this method allows to set the coordinates int[] array
     */
    public void setThirdValue(int[][] sankCoordinates) {
        this.sankCoordinates = sankCoordinates;
    }

    /**
     * this method returns ship object
     */
    public int[] getFirstValue(){
        return this.shipCoordinates;
    }

    /**
     * this method returns message value
     */
    public String getSecondValue() {
        return this.message;
    }

    /**
     * this method returns int[] of coordinates, where int[0] == coordX and int[1] == coordY
     */
    public int[][] getThirdValue() {
        return this.sankCoordinates;
    }
    
}
