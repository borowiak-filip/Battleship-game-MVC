package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import model.GameModel;


// @borowiak-filip
public class GameModelTest {

    GameModel model;
    String[][] boardLayoutMemory;
    List<int[][]> shipCoords = new ArrayList<>();

    @Before
    public void init() {

        // init Mock model object 
        model = new GameModel();
        // set up one ship for test functionality purposes

        int [][] shipPartsCoordinates_2_1 = {{2, 2}, {3, 2}};
        int [][] shipPartsCoordinates_2_2 = {{2, 3}, {3, 3}};
        
        shipCoords.add(shipPartsCoordinates_2_1);
        shipCoords.add(shipPartsCoordinates_2_2);
        

        model.initShips(shipCoords);


    }
    /**
     * Test if Game model marks GameOver when all ships are down
     */
    @Test
    public void testGameOver() {

        model.change(2, 2);
        model.change(3, 2);
        model.change(2, 3);
        model.change(3, 3);
        model.checkIfGameOver();

        assertEquals(model.checkIfGameOver(), true);
    }
    /**
     * Test if Game model throws Index out of Bounds Exception if model got through illegal values
     */
    @Test
    public void testCoorrectCoordinatesException() {

            int coordX = 11;
            int coordY = 8;

            assertThrows(IndexOutOfBoundsException.class, () -> {
                model.change(coordX, coordY);
            });
    }
    
    /**
     * Test if Game model marks Ship Sunk upon hitting all of its parts
     */
    @Test
    public void testSikingShip(){
       
        model.change(2, 2);
        model.change(3, 2);
        String till_1 = model.getBoardLayout()[2][2];
        String till_2 = model.getBoardLayout()[3][2];

        assertEquals(till_1, extras.ShipStatus.X.toString());
        assertEquals(till_2, extras.ShipStatus.X.toString());


    }
}
