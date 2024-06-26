package controller;

import java.util.List;
import java.util.Observer;

import extras.Messages;
import extras.Triple;
import interfaces.IController;
import model.GameModel;
import view.JView;

//@borowiak-filip

/**
 * Controller Object of the MVC pattern. It is a broker between user and model.
 */
@SuppressWarnings("deprecation")
public class GameController implements IController{

    /**
     * GameController has one instance of model 
     */
    private GameModel model; // Controller has one model game
    private JView view; // Controller has one view instance

    public GameController(GameModel model){this.model = model;}

    public void setView(JView view) {
        this.view = view;
        this.view.displayUserMessage(Messages.GUI_WELCOME);
    }


    @Override
    public void initialize( List<int[][]> shipCoords) {
        this.model.initialize(shipCoords);
        
    }

    @Override
    public void change(int coordX, int coordY) {
        if (this.model.isValid(coordX, coordY)) { // check if coordinates are valid request 
            if (!this.model.hasBeenHitBefore(coordX, coordY)) { // check if field has been hit / checked before 
                Triple results = this.model.change(coordX, coordY);
                int shipCoordX = results.getFirstValue()[0];
                int shipCoordY = results.getFirstValue()[1];

                if (results.getSecondValue().equals(Messages.GOOD_SHOT)) {
                    this.view.setShipMarkerToHit(shipCoordX, shipCoordY);
                    this.view.disableButton(shipCoordX, shipCoordY);
                }
                else if (results.getSecondValue().equals(Messages.SUNK_SHIP)) {
                    this.view.setShipMarkerToHit(shipCoordX, shipCoordY);
                    this.view.disableButton(shipCoordX, shipCoordY);
                    this.view.setShipSunk(results.getThirdValue());
                }
                else {
                    this.view.setShipMarkerToMiss(shipCoordX, shipCoordY);
                    this.view.disableButton(shipCoordX, shipCoordY);
                }

                if(this.model.checkIfGameOver()) {this.view.displayGameOver();}

            }else { this.view.displayUserMessage(Messages.FIELD_CHECKED);}
            
            
        }else { this.view.displayUserMessage(Messages.NUMBER_OUT_OF_BOUND_EXCEPTION);}
        
    }


    public void addObserver(Observer o) {this.model.addObserver(o);}

    public GameModel getGameModel() {return this.model.getGameModel();}

    
}
